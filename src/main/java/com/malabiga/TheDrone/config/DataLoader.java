package com.malabiga.TheDrone.config;

import com.malabiga.TheDrone.data.DataPackage.StateCategory;
import com.malabiga.TheDrone.data.DataPackage.ModelCategory;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.model.Medication;
import com.malabiga.TheDrone.repository.DroneRepository;
import com.malabiga.TheDrone.repository.MedicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(DroneRepository droneRepo, MedicationRepository medRepo) {
        return args -> {

            Drone drone1 = new Drone();
            drone1.setSerialNumber("DRN001");
            drone1.setModel(ModelCategory.LIGHTWEIGHT);
            drone1.setWeightLimit(300);
            drone1.setBatteryCapacity(80);
            drone1.setState(StateCategory.IDLE);

            Drone drone2 = new Drone();
            drone2.setSerialNumber("DRN002");
            drone2.setModel(ModelCategory.MIDDLEWEIGHT);
            drone2.setWeightLimit(500);
            drone2.setBatteryCapacity(60);
            drone2.setState(StateCategory.IDLE);

            droneRepo.saveAll(Arrays.asList(drone1, drone2));

            Medication med1 = new Medication();
            med1.setName("PainKiller_500");
            med1.setWeight(100);
            med1.setCode("PK500_A");
            med1.setImage("image-url-1");
            med1.setDrone(drone1);

            Medication med2 = new Medication();
            med2.setName("ColdTab-100");
            med2.setWeight(150);
            med2.setCode("CT100_B");
            med2.setImage("image-url-2");
            med2.setDrone(drone1);

            medRepo.saveAll(Arrays.asList(med1, med2));
        };
    }
}
