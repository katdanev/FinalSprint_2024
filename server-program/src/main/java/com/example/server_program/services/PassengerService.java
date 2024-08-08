package com.example.server_program.services;

import com.example.server_program.entities.Airport;
import com.example.server_program.entities.Flight;
import com.example.server_program.entities.Passenger;
import com.example.server_program.repositories.AirportRepository;
import com.example.server_program.repositories.FlightRepository;
import com.example.server_program.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    // CRUD operations
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long passengerId, Passenger passengerDetails) {
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);
        if (passengerOpt.isPresent()) {
            Passenger passenger = passengerOpt.get();
            passenger.setFirstName(passengerDetails.getFirstName());
            passenger.setLastName(passengerDetails.getLastName());
            passenger.setBirthDate(passengerDetails.getBirthDate());
            passenger.setPhoneNumber(passengerDetails.getPhoneNumber());
            passenger.setCity_id(passengerDetails.getCity_id());
            return passengerRepository.save(passenger);
        } else {
            throw new RuntimeException("Passenger not found with id " + passengerId);
        }
    }

    public void deletePassenger(Long passengerId) {
        if (passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
        } else {
            throw new RuntimeException("Passenger not found with id " + passengerId);
        }
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    // passengers and used airports
    private List<String> getAirportsUsedByPassenger(Long passengerId) {
        List<Flight> flights = flightRepository.findAll().stream()
                .filter(flight -> flight.getPassengers_ids().contains(passengerId.toString()))
                .collect(Collectors.toList());

        Set<Long> airportIds = flights.stream()
                .flatMap(flight -> Stream.of(flight.getDeparture_airport_id(), flight.getArrival_airport_id()))
                .collect(Collectors.toSet());

        List<Airport> airports = airportRepository.findAllById(airportIds);

        return airports.stream()
                .map(Airport::getName)
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> getAirportsUsedByAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        Map<String, List<String>> passengerAirportsMap = new HashMap<>();

        for (Passenger passenger : passengers) {
            Long passengerId = passenger.getPassenger_id();
            String passengerName = passenger.getFirstName() + " " + passenger.getLastName();
            List<String> airports = getAirportsUsedByPassenger(passengerId);
            passengerAirportsMap.put(passengerName, airports);
        }

        return passengerAirportsMap;
    }
}
