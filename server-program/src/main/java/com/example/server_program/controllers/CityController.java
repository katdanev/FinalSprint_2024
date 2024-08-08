package com.example.server_program.controllers;

import com.example.server_program.entities.City;
import com.example.server_program.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // create
    @PostMapping("/create")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City createdCity = cityService.createCity(city);
        return ResponseEntity.ok(createdCity);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get all
    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        City city = cityService.updateCity(id, updatedCity);
        return ResponseEntity.ok(city);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
