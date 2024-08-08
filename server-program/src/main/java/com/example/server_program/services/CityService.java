package com.example.server_program.services;

import com.example.server_program.entities.City;
import com.example.server_program.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // CRUD operations
    public City createCity(City city) {

        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {
        if (!cityRepository.existsById(id)) {
            throw new EntityNotFoundException("City not found with id: " + id);
        }
        updatedCity.setCity_id(id);
        return cityRepository.save(updatedCity);
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
