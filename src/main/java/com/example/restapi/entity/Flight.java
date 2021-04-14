package com.example.restapi.entity;

import javax.persistence.*;

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

}
