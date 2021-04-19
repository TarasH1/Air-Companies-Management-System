package com.example.restapi.repository;

import com.example.restapi.entity.AirCompany;
import com.example.restapi.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByFlightStatus(Flight.FlightStatus status);

    @Transactional
    void deleteById(Long id);
}
