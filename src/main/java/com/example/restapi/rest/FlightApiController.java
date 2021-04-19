package com.example.restapi.rest;

import com.example.restapi.entity.AirCompany;
import com.example.restapi.entity.Airplane;
import com.example.restapi.entity.Flight;
import com.example.restapi.repository.AirCompanyRepository;
import com.example.restapi.repository.AirplaneRepository;
import com.example.restapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/flight")
public class FlightApiController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirCompanyRepository airCompanyRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    public static class FlightCreateRequestBody {

        private Long airCompanyId;
        private Long airplaneId;
        private LocalDateTime createdAt;
        private LocalDateTime delayStartedAt;
        private LocalDateTime endedAt;
        private LocalTime estimatedFlightTime;
        private String departureCountry;
        private String destinationCountry;
        private Integer distance;

        public Long getAirCompanyId() {
            return airCompanyId;
        }

        public void setAirCompanyId(Long airCompanyId) {
            this.airCompanyId = airCompanyId;
        }

        public Long getAirplaneId() {
            return airplaneId;
        }

        public void setAirplaneId(Long airplaneId) {
            this.airplaneId = airplaneId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getDelayStartedAt() {
            return delayStartedAt;
        }

        public void setDelayStartedAt(LocalDateTime delayStartedAt) {
            this.delayStartedAt = delayStartedAt;
        }

        public LocalDateTime getEndedAt() {
            return endedAt;
        }

        public void setEndedAt(LocalDateTime endedAt) {
            this.endedAt = endedAt;
        }

        public LocalTime getEstimatedFlightTime() {
            return estimatedFlightTime;
        }

        public void setEstimatedFlightTime(LocalTime estimatedFlightTime) {
            this.estimatedFlightTime = estimatedFlightTime;
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

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }
    }

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    @GetMapping(value = "/status", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllAirCompaniesFlightsByStatus(@RequestParam Flight.FlightStatus status, String name) {

        List<Flight> flightList = flightRepository.findByFlightStatus(status);

        flightList = flightList.stream().filter(flight -> flight.getAirCompany().getAirCompanyName().equals(name)).collect(Collectors.toList());

        return flightList;
    }

    @GetMapping(value = "/active", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllFlightsInActiveStatus() {

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime fromNowMinus24h = currentTime.minusHours(24);
        List<Flight> flightList = flightRepository.findByFlightStatus(Flight.FlightStatus.ACTIVE);
        flightList = flightList.stream().filter(flight -> flight.getCreatedAt().isBefore(fromNowMinus24h)).collect(Collectors.toList());

        return flightList;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Flight addNewFlight(@RequestBody FlightCreateRequestBody body) {

        AirCompany airCompany = airCompanyRepository.findById(body.airCompanyId).get();
        Airplane airplane = airplaneRepository.findById(body.airplaneId).get();

        Flight flight = new Flight();

        flight.setAirCompany(airCompany);
        flight.setAirplane(airplane);
        flight.setFlightStatus(Flight.FlightStatus.PENDING);
        flight.setDepartureCountry(body.departureCountry);
        flight.setDestinationCountry(body.destinationCountry);
        flight.setDistance(body.distance);
        flight.setCreatedAt(body.createdAt);
        flight.setDelayStartedAt(body.delayStartedAt);
        flight.setEndedAt(body.endedAt);
        flight.setEstimatedFlightTime(body.estimatedFlightTime);

        flightRepository.save(flight);

        return flight;
    }

    @PutMapping(value = "/change_status", consumes = "application/json", produces = "application/json")
    public Flight changeStatus(@RequestParam Long flightId, Flight.FlightStatus status, String time) {

        Flight flight = flightRepository.findById(flightId).get();

        switch (status) {
            case DELAYED:
                flight.setFlightStatus(status);
                flight.setDelayStartedAt(LocalDateTime.parse(time));
                break;
            case ACTIVE:
                flight.setFlightStatus(status);
                flight.setCreatedAt(LocalDateTime.parse(time));
                break;
            case COMPLETED:
                flight.setFlightStatus(status);
                flight.setEndedAt(LocalDateTime.parse(time));
                break;
            case PENDING:
                flight.setFlightStatus(status);
                break;

        }

        flightRepository.save(flight);

        return flight;
    }

    @GetMapping(value = "/completed", consumes = "application/json", produces = "application/json")
    public List<Flight> findAllCompletedFlights() {

        List<Flight> flightList = flightRepository.findByFlightStatus(Flight.FlightStatus.COMPLETED);

        flightList = flightList.stream().filter(flight -> {
            LocalTime createdAt = flight.getCreatedAt().toLocalTime();
            LocalTime endedAt = flight.getEndedAt().toLocalTime();
            int estimatedTime = flight.getEstimatedFlightTime().toSecondOfDay();
            long diff = ChronoUnit.SECONDS.between(createdAt, endedAt);
            return diff > estimatedTime;
        }).collect(Collectors.toList());

        return flightList;
    }

}
