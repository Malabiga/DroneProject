package com.malabiga.TheDrone.service;

import com.malabiga.TheDrone.condition.Model;
import com.malabiga.TheDrone.model.Drone;
import com.malabiga.TheDrone.repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneServiceTest {
    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneServiceImpl  droneServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDrone() {
        Drone drone = new Drone();
        drone.setSerialNumber("DRN001");
        drone.setModel(Model.WeightCategory.LIGHTWEIGHT);
        drone.setBatteryCapacity(90);
        drone.setWeightLimit(300);
        drone.setState(Model.State.IDLE);

        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        Drone saved = droneServiceImpl.registerDrone(drone);

        assertNotNull(saved);
        assertEquals("DRN001", saved.getSerialNumber());
        verify(droneRepository, times(1)).save(drone);
    }

    @Test
    void testGetDroneBatteryLevel() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setBatteryCapacity(75);

        when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));

        int battery = droneServiceImpl.getDroneBatteryLevel(1L);

        assertEquals(75, battery);
        verify(droneRepository, times(1)).findById(1L);
    }
}