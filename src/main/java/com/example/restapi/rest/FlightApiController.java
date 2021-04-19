package com.example.restapi.rest;

import com.example.restapi.entity.AirCompany;
import com.example.restapi.entity.Flight;
import com.example.restapi.repository.AirCompanyRepository;
import com.example.restapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/flight")
public class FlightApiController {

    @Autowired
    private FlightRepository flightRepository;
    private AirCompanyRepository airCompanyRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    @GetMapping(value = "/status", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllAirCompaniesFlightsByStatus(@RequestParam Flight.FlightStatus status, String name) {

        List<Flight> flightList = flightRepository.findByFlightStatus(status);
        System.out.println(flightList);

        flightList = flightList.stream().filter(flight -> flight.getAirCompany().getAirCompanyName().equals(name)).collect(Collectors.toList());

        return flightList;
    }

    @GetMapping(value = "/active", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllFlightsInActiveStatus() {

        long now = System.currentTimeMillis();
        Time sqlTime = new Time(now);
        long fromNowPlus24h = sqlTime.getTime() + Time.valueOf("24:00:00").getTime();
        List<Flight> flightList = flightRepository.findByFlightStatus(Flight.FlightStatus.ACTIVE);
        flightList = flightList.stream().filter(flight -> flight.getCreatedAt().getTime() > fromNowPlus24h).collect(Collectors.toList());

        return flightList;
    }

}
