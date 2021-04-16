package com.example.restapi.repository;

import com.example.restapi.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {



    @Transactional
    void deleteById(Long id);
}
