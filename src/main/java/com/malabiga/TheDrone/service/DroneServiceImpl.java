package com.malabiga.TheDrone.service;

import com.malabiga.TheDrone.data.DataPackage.StateCategory;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;
import com.malabiga.TheDrone.repository.DroneRepository;
import com.malabiga.TheDrone.repository.MedicationRepository;
import com.malabiga.TheDrone.scheduler.DroneScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DroneServiceImpl implements DroneService {

    private static final Logger log = LoggerFactory.getLogger(DroneServiceImpl.class);

    /* INITIALIZATION */
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    /* CONSTRUCTOR INJECTION */
    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    /* Registering a drone */
    @Override
    public Drone registerDrone(Drone drone) {
        log.info("Registered Drone: {}", drone);
        return droneRepository.save(drone);
    }

    /* Loading a drone with medication */
    @Override
    public Drone loadDroneWithMedications(Long droneId, List<Medication> medications) {

        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));

        /* Prevent drone enter to LOADING state is the battery level is below 25%. */
        if (drone.getBatteryCapacity() < 25) {
            log.error("Drone battery is {}, too low for loading", drone.getBatteryCapacity());
            throw new IllegalStateException("Drone battery too low for loading.");
        }

        /* Prevent drone been overloaded than maximum capacity */
        int currentWeight = drone.getMedications().stream().mapToInt(Medication::getWeight).sum();
        int addedWeight = medications.stream().mapToInt(Medication::getWeight).sum();

        if (currentWeight + addedWeight > drone.getWeightLimit()) {
            log.error("Loading exceeds drone weight limit.");
            throw new IllegalArgumentException("Loading exceeds drone weight limit.");
        }

        for (Medication medication : medications) {
            medication.setDrone(drone);
            log.info("Successfully saved medication: {}", medication);
            medicationRepository.save(medication);
        }

        drone.getMedications().addAll(medications);
        drone.setState(StateCategory.LOADING);
        return droneRepository.save(drone);
    }

    /* Checking loaded medications for a given drone */
    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));
        log.info("Loaded medication(s): {}",drone.getMedications());
        return drone.getMedications();
    }

    /* Check drone availability for loading */
    @Override
    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findAll().stream()
                .filter(drone -> drone.getState() == StateCategory.IDLE && drone.getBatteryCapacity() >= 25)
                .collect(Collectors.toList());
    }

    /* Check drone information (Battery) */
    @Override
    public int getDroneBattery(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));
        log.info("Drone Battery Percentage: {}",drone.getBatteryCapacity());
        return drone.getBatteryCapacity();
    }
}