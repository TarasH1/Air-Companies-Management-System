package com.example.restapi.rest;

import com.example.restapi.entity.Airplane;
import com.example.restapi.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/airplane")
public class AirplaneApiController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping
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
}
