package com.malabiga.TheDrone.service;


import com.malabiga.TheDrone.condition.Model.State;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;
import com.malabiga.TheDrone.repository.DroneRepository;
import com.malabiga.TheDrone.repository.MedicationRepository;
import com.malabiga.TheDrone.service.DroneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone loadMedications(Long droneId, List<Medication> medications) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));

        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery too low for loading.");
        }

        int currentWeight = drone.getMedications().stream().mapToInt(Medication::getWeight).sum();
        int addedWeight = medications.stream().mapToInt(Medication::getWeight).sum();

        if (currentWeight + addedWeight > drone.getWeightLimit()) {
            throw new IllegalArgumentException("Loading exceeds drone weight limit.");
        }

        for (Medication medication : medications) {
            medication.setDrone(drone);
            medicationRepository.save(medication);
        }

        drone.getMedications().addAll(medications);
        drone.setState(State.LOADING);
        return droneRepository.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));
        return drone.getMedications();
    }

    @Override
    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findAll().stream()
                .filter(drone -> drone.getState() == State.IDLE && drone.getBatteryCapacity() >= 25)
                .collect(Collectors.toList());
    }

    @Override
    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found"));
        return drone.getBatteryCapacity();
    }
}