package com.example.restapi.entity;

import javax.persistence.*;

@Entity(name = "aircompany")
public class AirCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String airCompanyName;

    @Column
    private String companyType;

    @Column
    private String foundedAt;

    public Long getId() {
        return id;
    }

    public String getAirCompanyName() {
        return airCompanyName;
    }

    public void setAirCompanyName(String airCompanyName) {
        this.airCompanyName = airCompanyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getFoundedAt() {
        return foundedAt;
    }

    public void setFoundedAt(String foundedAt) {
        this.foundedAt = foundedAt;
    }
}
