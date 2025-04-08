package com.malabiga.TheDrone.repository;

import com.malabiga.TheDrone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
