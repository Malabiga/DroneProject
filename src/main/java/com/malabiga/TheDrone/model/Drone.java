package com.malabiga.TheDrone.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.malabiga.TheDrone.data.DataPackage;
import com.malabiga.TheDrone.data.DataPackage.ModelCategory;
import com.malabiga.TheDrone.data.DataPackage.StateCategory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {

    /* INITIALIZATION */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private ModelCategory model;

    private int weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private StateCategory state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Medication> medications = new ArrayList<>();

    /* CONSTRUCTORS */
    public Drone() {}

    public Drone(String serialNumber, ModelCategory model, int weightLimit, int batteryCapacity, StateCategory state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    /* GETTERS AND SETTERS */
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

    public DataPackage.ModelCategory getModel() {
        return model;
    }

    public void setModel(DataPackage.ModelCategory model) {
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

    public StateCategory getState() {
        return state;
    }

    public void setState(StateCategory state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

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
