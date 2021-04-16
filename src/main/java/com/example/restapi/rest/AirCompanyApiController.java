package com.example.restapi.rest;

import com.example.restapi.entity.AirCompany;
import com.example.restapi.entity.Airplane;
import com.example.restapi.repository.AirCompanyRepository;
import com.example.restapi.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/aircompany")
public class AirCompanyApiController {

    @Autowired
    private AirCompanyRepository airCompanyRepository;
    private AirplaneRepository airplaneRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<AirCompany> findAllAirCompanies() {
        return (List<AirCompany>) airCompanyRepository.findAll();
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AirCompany> getById(@PathVariable Long id) {
        Optional<AirCompany> airCompany = airCompanyRepository.findById(id);

        return airCompany.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AirCompany saveAirCompany(@Validated @RequestBody AirCompany airCompany) {
        return airCompanyRepository.save(airCompany);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public AirCompany update(@PathVariable Long id, @RequestParam String airCompanyName, String companyType,String foundedAt) {
        AirCompany airCompany = airCompanyRepository.findById(id).get();

        airCompany.setAirCompanyName(airCompanyName);
        airCompany.setCompanyType(companyType);
        airCompany.setFoundedAt(foundedAt);

        airCompanyRepository.save(airCompany);

        return airCompany;
    }

    @PostMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public AirCompany reassignPlane(@PathVariable Long airCompanyId, @RequestParam Long airplaneId) {
        AirCompany airCompany = airCompanyRepository.findById(airCompanyId).get();
        Airplane airplane = airplaneRepository.findById(airplaneId).get();

        airCompany.addAirplane(airplane);
        airplane.setAirCompany(airCompany);

        airCompanyRepository.save(airCompany);
        return airCompany;
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void deleteById(@PathVariable Long id) {
        airCompanyRepository.deleteById(id);
    }
}
