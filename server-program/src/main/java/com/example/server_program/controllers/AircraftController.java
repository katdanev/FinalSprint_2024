package com.example.server_program.controllers;

import com.example.server_program.entities.Aircraft;
import com.example.server_program.services.AircraftService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/aircrafts")
@AllArgsConstructor
public class AircraftController {

    private AircraftService aircraftService;

    // create
    @PostMapping("/create")
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft) {
        Aircraft savedAircraft = aircraftService.saveAircraft(aircraft);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAircraft);
    }

    // get all
    @GetMapping("/all")
    public ResponseEntity<List<Aircraft>> getAllAircrafts() {
        List<Aircraft> aircrafts = aircraftService.getAllAircrafts();
        return ResponseEntity.ok(aircrafts);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        if (aircraftService.getAircraftById(id).isPresent()) {
            aircraft.setAircraft_id(id); // Оновлюємо ID
            Aircraft updatedAircraft = aircraftService.saveAircraft(aircraft);
            return ResponseEntity.ok(updatedAircraft);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id)
                .map(aircraft -> ResponseEntity.ok(aircraft))
                .orElse(ResponseEntity.notFound().build());
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
        if (aircraftService.getAircraftById(id).isPresent()) {
            aircraftService.deleteAircraft(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // aircrafts with last flight and passengers
    @GetMapping("/with-passengers")
    public ResponseEntity<List<String>> getAircraftsWithPassengers() {
        List<String> aircraftsInfo = aircraftService.getAircraftsWithPassengerInfo();
        return ResponseEntity.ok(aircraftsInfo);
    }
}

