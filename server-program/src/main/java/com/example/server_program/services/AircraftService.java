package com.example.server_program.services;

import com.example.server_program.entities.Aircraft;
import com.example.server_program.entities.Flight;
import com.example.server_program.entities.Passenger;
import com.example.server_program.repositories.AircraftRepository;
import com.example.server_program.repositories.FlightRepository;
import com.example.server_program.repositories.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AircraftService {

    private AircraftRepository aircraftRepository;
    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;

    // CRUD operations
    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Optional<Aircraft> getAircraftById(Long id) {
        return aircraftRepository.findById(id);
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }

    // aircrafts with passengers
    public List<String> getAircraftsWithPassengerInfo() {
        List<Flight> flights = flightRepository.findAll();

        Map<Long, List<Flight>> flightsByAircraft = flights.stream()
                .collect(Collectors.groupingBy(Flight::getAircraft_id));

        return flightsByAircraft.entrySet().stream()
                .map(entry -> {
                    Long aircraftId = entry.getKey();
                    List<Flight> aircraftFlights = entry.getValue();
                    Flight latestFlight = aircraftFlights.stream()
                            .max(Comparator.comparing(Flight::getFlightDate))
                            .orElse(null);

                    if (latestFlight != null) {
                        List<Long> passengerIds = extractPassengerIds(latestFlight);
                        List<Passenger> passengers = passengerRepository.findAllById(passengerIds);

                        String passengersList = passengers.stream()
                                .map(passenger -> passenger.getFirstName() + " " + passenger.getLastName())
                                .collect(Collectors.joining(", "));

                        return aircraftRepository.findById(aircraftId)
                                .map(aircraft -> aircraft.getType() + " - " + latestFlight.getFlightDate() + ", Passengers: " + passengersList)
                                .orElse("Aircraft not found");
                    } else {
                        return "No flights for aircraft ID " + aircraftId;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Long> extractPassengerIds(Flight flight) {
        String[] passengerIdStrings = flight.getPassengers_ids().split(",");
        return Arrays.stream(passengerIdStrings)
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}

