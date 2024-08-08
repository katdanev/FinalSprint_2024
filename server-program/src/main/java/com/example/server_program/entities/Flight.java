package com.example.server_program.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flight_id;

    @Column(name = "departure_airport_id", nullable = false)
    private Long departure_airport_id;

    @Column(name = "arrival_airport_id", nullable = false)
    private Long arrival_airport_id;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraft_id;

    @Column(name = "flightDate", nullable = false)
    private LocalDateTime flightDate;

    @Column(name = "passengers_ids", nullable = false)
    private String passengers_ids;

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public Long getDeparture_airport_id() {
        return departure_airport_id;
    }

    public void setDeparture_airport_id(Long departure_airport_id) {
        this.departure_airport_id = departure_airport_id;
    }

    public Long getArrival_airport_id() {
        return arrival_airport_id;
    }

    public void setArrival_airport_id(Long arrival_airport_id) {
        this.arrival_airport_id = arrival_airport_id;
    }

    public Long getAircraft_id() {
        return aircraft_id;
    }

    public void setAircraft_id(Long aircraft_id) {
        this.aircraft_id = aircraft_id;
    }


    public LocalDateTime getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDateTime flightDate) {
        this.flightDate = flightDate;
    }

    public String getPassengers_ids() {
        return passengers_ids;
    }

    public void setPassengers_ids(String passengers_ids) {
        this.passengers_ids = passengers_ids;
    }
}
