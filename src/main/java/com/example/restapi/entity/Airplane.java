package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String airplaneName;

    @Column
    private String factorySerialNumber;

    @Column
    private String airCompanyId;

    @Column
    private String numberOfFlights;

    @Column
    private String flightDistance;

    @Column
    private String fuelCapacity;

    @Column
    private String type;

    @Column
    private String createdAt;

    @JoinColumn
    @JsonIgnore
    @OneToOne(mappedBy = "airplane")
    private AirCompany airCompany;

    public Long getId() {
        return id;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getFactorySerialNumber() {
        return factorySerialNumber;
    }

    public void setFactorySerialNumber(String factorySerialNumber) {
        this.factorySerialNumber = factorySerialNumber;
    }

    public String getAirCompanyId() {
        return airCompanyId;
    }

    public void setAirCompanyId(String airCompanyId) {
        this.airCompanyId = airCompanyId;
    }

    public String getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(String numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public String getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(String flightDistance) {
        this.flightDistance = flightDistance;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
