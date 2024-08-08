package com.example.server_program.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Long airport_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "city_id", nullable = false)
    private Long city_id;

    @Column(name = "isWorkingNow", nullable = false)
    private boolean isWorkingNow;

    public Long getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Long airport_id) {
        this.airport_id = airport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public boolean isWorkingNow() {
        return isWorkingNow;
    }

    public void setWorkingNow(boolean workingNow) {
        isWorkingNow = workingNow;
    }
}
