package com.example.restapi.repository;

import com.example.restapi.entity.AirCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AirCompanyRepository extends CrudRepository<AirCompany, Long> {

    List<AirCompany> findByAirCompanyName(String airCompanyName);

    @Transactional
    void deleteByAirCompanyName(String airCompanyName);

    @Transactional
    void deleteById(Long id);
}
