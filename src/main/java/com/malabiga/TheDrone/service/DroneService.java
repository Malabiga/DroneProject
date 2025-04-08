package com.malabiga.TheDrone.service;

import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;

import java.util.List;

public interface DroneService {

    Drone registerDrone(Drone drone);
    Drone loadMedications(Long droneId, List<Medication> medications);
    List<Medication> getLoadedMedications(Long droneId);
    List<Drone> getAvailableDronesForLoading();
    int getDroneBatteryLevel(Long droneId);

}
