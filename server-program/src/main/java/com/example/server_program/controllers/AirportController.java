package com.example.server_program.controllers;

import com.example.server_program.entities.Airport;
import com.example.server_program.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.saveAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
    }

    // get all
    @GetMapping("/all")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        if (!airportService.getAirportById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        airport.setAirport_id(id);
        Airport updatedAirport = airportService.saveAirport(airport);
        return ResponseEntity.ok(updatedAirport);
    }

    // read airport by id
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Optional<Airport> airport = airportService.getAirportById(id);
        return airport.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    // list of airports with cities
    @GetMapping("/with-cities")
    public ResponseEntity<List<String>> getAirportsWithCities() {
        List<String> airportsWithCities = airportService.getAirportsWithCities();
        return ResponseEntity.ok(airportsWithCities);
    }

    // airports which is working
    @GetMapping("/which-is-working")
    public List<String> getOperationalAirportsWithLastFlightInfo() {
        return airportService.getOperationalAirports();
    }
}