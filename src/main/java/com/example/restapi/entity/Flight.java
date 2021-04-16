package com.example.restapi.entity;

import javax.persistence.*;
import java.sql.Time;

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
    private Time estimatedFlightTime;

    @Column
    private Time endedAt;

    @Column
    private Time delayStartedAt;

    @Column
    private Time createdAt;

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

    public Time getEstimatedFlightTime() {
        return estimatedFlightTime;
    }

    public void setEstimatedFlightTime(Time estimatedFlightTime) {
        this.estimatedFlightTime = estimatedFlightTime;
    }

    public Time getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Time endedAt) {
        this.endedAt = endedAt;
    }

    public Time getDelayStartedAt() {
        return delayStartedAt;
    }

    public void setDelayStartedAt(Time delayStartedAt) {
        this.delayStartedAt = delayStartedAt;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
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
