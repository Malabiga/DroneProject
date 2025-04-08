package com.malabiga.TheDrone.repository;

import com.malabiga.TheDrone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

/* PERFORM CRUD OPERATIONS ON MEDICATION ENTITY */
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
