package com.malabiga.TheDrone.entities.typedmetadata;

import com.malabiga.TheDrone.data.DataPackage.ModelCategory;
import com.malabiga.TheDrone.data.DataPackage.StateCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/* This class is just an optional if we want to exclude the ID to the JSON Response */
public class DroneDTO {

    /* INITIALIZATION */
    @Column(length = 100, nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private ModelCategory model;

    private int weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private StateCategory state;

    /* CONSTRUCTORS */
    public DroneDTO() {}

    public DroneDTO(String serialNumber, ModelCategory model, int weightLimit, int batteryCapacity, StateCategory state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    /* GETTERS AND SETTERS */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ModelCategory getModel() {
        return model;
    }

    public void setModel(ModelCategory model) {
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

    @Override
    public String toString() {
        return "DroneDTO{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                '}';
    }
}
