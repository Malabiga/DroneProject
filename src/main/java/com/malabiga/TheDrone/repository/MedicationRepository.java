package com.malabiga.TheDrone.repository;

import com.malabiga.TheDrone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
