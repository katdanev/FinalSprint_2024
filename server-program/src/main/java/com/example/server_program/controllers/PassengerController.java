package com.example.server_program.controllers;

import com.example.server_program.entities.Passenger;
import com.example.server_program.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // create
    @PostMapping("/create")
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    // get all
    @GetMapping("/all")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // update
    @PutMapping("/update/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    // passengers and used airports
    @GetMapping("/used-airports")
    public ResponseEntity<Map<String, List<String>>> getAirportsUsedByAllPassengers() {
        Map<String, List<String>> passengerAirportsMap = passengerService.getAirportsUsedByAllPassengers();
        return ResponseEntity.ok(passengerAirportsMap);
    }
}
