package com.example.server_program.services;

import com.example.server_program.entities.Flight;
import com.example.server_program.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.server_program.repositories.AirportRepository;
import com.example.server_program.repositories.CityRepository;
import com.example.server_program.entities.Airport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportService {

    private AirportRepository airportRepository;
    private CityRepository cityRepository;

    private FlightRepository flightRepository;

    // CRUD operations
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    // airports with cities
    public List<String> getAirportsWithCities() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> {
                    Long city_id = airport.getCity_id(); // Припускаємо, що Airport має поле cityId
                    return cityRepository.findById(city_id)
                            .map(city -> airport.getName() + " - " + city.getName())
                            .orElse(airport.getName() + " - No City");
                })
                .collect(Collectors.toList());
    }

    // working airports
    public List<String> getOperationalAirports() {
        return airportRepository.findAll().stream()
                .filter(Airport::isWorkingNow)
                .map(Airport::getName)
                .collect(Collectors.toList());
    }
}
