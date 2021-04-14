package com.example.restapi.repository;

import com.example.restapi.entity.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    Airplane findByAirplaneName(String airplaneName);

    @Transactional
    void deleteByAirplaneName(String airplaneName);

    @Transactional
    void deleteById(Long id);

}
