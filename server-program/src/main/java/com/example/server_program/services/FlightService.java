package com.example.server_program.services;

import com.example.server_program.entities.Flight;
import com.example.server_program.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // CRUD operations
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setDeparture_airport_id(updatedFlight.getDeparture_airport_id());
                    flight.setArrival_airport_id(updatedFlight.getArrival_airport_id());
                    flight.setAircraft_id(updatedFlight.getAircraft_id());
                    flight.setFlightDate(updatedFlight.getFlightDate());
                    flight.setPassengers_ids(updatedFlight.getPassengers_ids());
                    return flightRepository.save(flight);
                })
                .orElseGet(() -> {
                    updatedFlight.setFlight_id(id);
                    return flightRepository.save(updatedFlight);
                });
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
