package com.malabiga.TheDrone.entities.typedmetadata;

import jakarta.validation.constraints.Pattern;

/* This class is just an optional if we want to exclude the ID to the JSON Response */
public class MedicationDTO {

    /* INITIALIZATION */
    @Pattern(regexp = "^[A-Za-z0-9-_]+$")
    private String name;

    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    private String image;

    /* CONSTRUCTORS */
    public MedicationDTO() {}

    public MedicationDTO(String name, int weight, String code, String image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    /* GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MedicationDTO{" +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
