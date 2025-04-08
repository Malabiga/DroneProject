package com.malabiga.TheDrone.controller;

import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;
import com.malabiga.TheDrone.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    /* Registering a drone */
    @PostMapping
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody Drone drone) {
        return ResponseEntity.ok(droneService.registerDrone(drone));
    }

    /* Loading a drone with medication */
    @PostMapping("/{droneId}/load")
    public ResponseEntity<Drone> loadDroneWithMedications(@PathVariable Long droneId, @Valid @RequestBody List<Medication> medications) {
        return ResponseEntity.ok(droneService.loadDroneWithMedications(droneId, medications));
    }

    /* Checking loaded medications for a given drone */
    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getLoadedMedications(droneId));
    }

    /* Check drone availability for loading */
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDronesForLoading());
    }

    /* Check drone information (Battery) */
    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> getDroneBattery(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getDroneBattery(droneId));
    }
}
