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

    // Register a new drone
    @PostMapping
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody Drone drone) {
        return ResponseEntity.ok(droneService.registerDrone(drone));
    }

    // Load medications onto a drone
    @PostMapping("/{droneId}/load")
    public ResponseEntity<Drone> loadDroneWithMedications(
            @PathVariable Long droneId,
            @Valid @RequestBody List<Medication> medications) {
        return ResponseEntity.ok(droneService.loadMedications(droneId, medications));
    }

    // Get medications loaded on a drone
    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getLoadedMedications(droneId));
    }

    // Get drones available for loading
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDronesForLoading());
    }

    // Get battery level of a specific drone
    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> getDroneBattery(@PathVariable Long droneId) {
        return ResponseEntity.ok(droneService.getDroneBatteryLevel(droneId));
    }
}
