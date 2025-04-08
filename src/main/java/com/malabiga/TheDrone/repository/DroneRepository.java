package com.malabiga.TheDrone.repository;

import com.malabiga.TheDrone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

/* PERFORM CRUD OPERATIONS ON DRONE ENTITY */
public interface DroneRepository extends JpaRepository<Drone, Long> {
}
