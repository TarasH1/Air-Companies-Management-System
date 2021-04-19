package com.example.restapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "flight")
public class Flight {

    public enum FlightStatus {
        ACTIVE,
        COMPLETED,
        DELAYED,
        PENDING,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated
    private FlightStatus flightStatus;

    @Column
    private String departureCountry;

    @Column
    private String destinationCountry;

    @Column
    private int distance;

    @Column
    private LocalTime estimatedFlightTime;

    @Column
    private LocalDateTime endedAt;

    @Column
    private LocalDateTime delayStartedAt;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn
    private AirCompany airCompany;

    @ManyToOne
    @JoinColumn
    private Airplane airplane;

    public Long getId() {
        return id;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalTime getEstimatedFlightTime() {
        return estimatedFlightTime;
    }

    public void setEstimatedFlightTime(LocalTime estimatedFlightTime) {
        this.estimatedFlightTime = estimatedFlightTime;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public LocalDateTime getDelayStartedAt() {
        return delayStartedAt;
    }

    public void setDelayStartedAt(LocalDateTime delayStartedAt) {
        this.delayStartedAt = delayStartedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return this.flightStatus.equals(FlightStatus.ACTIVE);
    }

    public boolean isCompleted() {
        return this.flightStatus.equals(FlightStatus.COMPLETED);
    }

    public boolean isDelayed() {
        return this.flightStatus.equals(FlightStatus.DELAYED);
    }

    public boolean isPending() {
        return this.flightStatus.equals(FlightStatus.PENDING);
    }
    public AirCompany getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(AirCompany airCompany) {
        this.airCompany = airCompany;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

}
