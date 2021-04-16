package com.example.restapi.rest;

import com.example.restapi.entity.Flight;
import com.example.restapi.repository.AirCompanyRepository;
import com.example.restapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Flight> findCompaniesFlightsByStatus() {
        List<Flight> flightList = (List<Flight>) flightRepository.findAll();

        flightList = flightList.stream().filter(Flight::isActive).collect(Collectors.toList());

        return flightList;
    }

}
