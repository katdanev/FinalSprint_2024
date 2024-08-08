package com.example.server_program.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Long aircraft_id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "airlineName", nullable = false)
    private String airlineName;

    @Column(name = "numberOfPassengers", nullable = false)
    private int numberOfPassengers;

    public Long getAircraft_id() {
        return aircraft_id;
    }

    public void setAircraft_id(Long aircraft_id) {
        this.aircraft_id = aircraft_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
