package com.example.restapi.rest;

import com.example.restapi.entity.AirCompany;
import com.example.restapi.entity.Airplane;
import com.example.restapi.repository.AirCompanyRepository;
import com.example.restapi.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/airplane")
public class AirplaneApiController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirCompanyRepository airCompanyRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Airplane> findAllAirplanes() {
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Airplane> getById(@PathVariable Long id) {
        Optional<Airplane> airplane = airplaneRepository.findById(id);

        return airplane.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Airplane saveAirplane(@Validated @RequestBody Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Airplane update(@PathVariable Long id, @RequestParam String airplaneName, String factorySerialNumber,
                           Long numberOfFlights, int flightDistance, int fuelCapacity, String type, LocalDate createdAt) {
        Airplane airplane = airplaneRepository.findById(id).get();

        airplane.setAirplaneName(airplaneName);
        airplane.setFactorySerialNumber(factorySerialNumber);
        airplane.setNumberOfFlights(numberOfFlights);
        airplane.setFlightDistance(flightDistance);
        airplane.setFuelCapacity(fuelCapacity);
        airplane.setType(type);
        airplane.setCreatedAt(createdAt);

        airplaneRepository.save(airplane);

        return airplane;
    }
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Airplane addAirplaneAndAssign(@RequestParam Long airCompanyId, String name, String serialNumber,
                                         Integer flightDistance, Integer fuelCapacity, Long numberOfFlights, String type) {

        AirCompany airCompany = airCompanyRepository.findById(airCompanyId).get();

        Airplane airplane = new Airplane();

        airplane.setAirCompany(airCompany);
        airplane.setAirplaneName(name);
        airplane.setFactorySerialNumber(serialNumber);
        airplane.setFlightDistance(flightDistance);
        airplane.setFuelCapacity(fuelCapacity);
        airplane.setNumberOfFlights(numberOfFlights);
        airplane.setType(type);

        airplaneRepository.save(airplane);

        return airplane;
    }


    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void deleteById(@PathVariable Long id) {
        airplaneRepository.deleteById(id);
    }
}
