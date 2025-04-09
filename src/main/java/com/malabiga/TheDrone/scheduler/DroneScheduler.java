package com.malabiga.TheDrone.scheduler;

import com.malabiga.TheDrone.data.DataPackage.StateCategory;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
public class DroneScheduler {
    private static final Logger log = LoggerFactory.getLogger(DroneScheduler.class);

    /* INITIALIZATION */
    private final DroneRepository droneRepository;
    private final Random random = new Random();

    /* CONSTRUCTOR */
    public DroneScheduler(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    /* Every 10 seconds simulate state transition and battery drain */
    @Transactional
    @Scheduled(fixedRateString = "${drone.scheduler.fixed-rate}")
    public void updateDroneStates() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {

            log.info("Processing drone: {} | Battery: {} | State: {}", drone.getId(), drone.getBatteryCapacity(), drone.getState());

            int battery = drone.getBatteryCapacity();

            /* Skip drones with 0% battery */
            if (battery <= 0) {
                drone.setState(StateCategory.IDLE);
                continue;
            }

            /* Simulate battery drain for active drones */
            if (drone.getState() == StateCategory.DELIVERING || drone.getState() == StateCategory.RETURNING) {
                /* 5–10% drain */
                battery -= random.nextInt(6) + 5;
                drone.setBatteryCapacity(Math.max(battery, 0));

                /* Simulate transition */
                if (drone.getState() == StateCategory.DELIVERING) {
                    drone.setState(StateCategory.DELIVERED);
                } else {
                    drone.setState(StateCategory.IDLE);
                }
            }

            /* If loaded, set as delivery */
            else if (drone.getState() == StateCategory.LOADED) {
                drone.setState(StateCategory.DELIVERING);
            }

            droneRepository.save(drone);
        }
    }
}
