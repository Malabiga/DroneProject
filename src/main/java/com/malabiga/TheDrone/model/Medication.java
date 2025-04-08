package com.malabiga.TheDrone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9-_]+$")
    private String name;

    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;

    private String image; // Base64 string or URL

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    // --- Constructors ---

    public Medication() {}

    public Medication(String name, int weight, String code, String image, Drone drone) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.drone = drone;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    // --- toString() ---

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
