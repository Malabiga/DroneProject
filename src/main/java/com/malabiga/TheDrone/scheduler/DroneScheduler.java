package com.malabiga.TheDrone.scheduler;

import com.malabiga.TheDrone.condition.Model.State;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.repository.DroneRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DroneScheduler {

    private final DroneRepository droneRepository;
    private final Random random = new Random();

    public DroneScheduler(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    // Every 15 seconds simulate state transition and battery drain
    @Scheduled(fixedRate = 15000)
    public void updateDroneStates() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            int battery = drone.getBatteryCapacity();

            // Skip drones with 0% battery
            if (battery <= 0) {
                drone.setState(State.IDLE);
                continue;
            }

            // Simulate battery drain for active drones
            if (drone.getState() == State.DELIVERING || drone.getState() == State.RETURNING) {
                battery -= random.nextInt(6) + 5; // 5–10% drain
                drone.setBatteryCapacity(Math.max(battery, 0));

                // Simulate transition
                if (drone.getState() == State.DELIVERING) {
                    drone.setState(State.DELIVERED);
                } else {
                    drone.setState(State.IDLE);
                }
            }

            // If loaded, simulate delivery
            else if (drone.getState() == State.LOADED) {
                drone.setState(State.DELIVERING);
            }

            droneRepository.save(drone);
        }
    }
}
