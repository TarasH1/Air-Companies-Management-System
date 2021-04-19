package com.example.restapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Long numberOfFlights;

    @Column
    private int flightDistance;

    @Column
    private int fuelCapacity;

    @Column
    private String type;

    @Column
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn
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

    public Long getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(Long numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(int flightDistance) {
        this.flightDistance = flightDistance;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public AirCompany getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(AirCompany airCompany) {
        this.airCompany = airCompany;
    }
}
