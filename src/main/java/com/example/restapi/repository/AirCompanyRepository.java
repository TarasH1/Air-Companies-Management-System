package com.example.restapi.repository;

import com.example.restapi.entity.AirCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCompanyRepository extends CrudRepository<AirCompany, Long> {
}
