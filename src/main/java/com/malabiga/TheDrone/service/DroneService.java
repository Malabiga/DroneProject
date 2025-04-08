package com.malabiga.TheDrone.service;

import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;

import java.util.List;


public interface DroneService {

    /* Registering a drone */
    Drone registerDrone(Drone drone);

    /* Loading a drone with medication */
    Drone loadDroneWithMedications(Long droneId, List<Medication> medications);

    /* Checking loaded medications for a given drone */
    List<Medication> getLoadedMedications(Long droneId);

    /* Check drone availability for loading */
    List<Drone> getAvailableDronesForLoading();

    /* Check drone information (Battery) */
    int getDroneBattery(Long droneId);
}
