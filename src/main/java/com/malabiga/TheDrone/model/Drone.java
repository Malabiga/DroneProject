package com.malabiga.TheDrone.model;

import com.malabiga.TheDrone.condition.Model.WeightCategory;
import com.malabiga.TheDrone.condition.Model.State;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private WeightCategory model;  // Now using WeightCategory

    private int weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private State state;  // Now using State

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications = new ArrayList<>();

    public Drone() {}

    public Drone(String serialNumber, WeightCategory model, int weightLimit, int batteryCapacity, State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public WeightCategory getModel() {
        return model;
    }

    public void setModel(WeightCategory model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                '}';
    }
}
