package com.kozich.airports.dao.entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class FlightEntity {
    private Integer flightId;
    private String flightNo;
    private OffsetDateTime scheduledDeparture;
    private LocalDateTime scheduledDepartureLocal;
    private OffsetDateTime scheduledArrival;
    private LocalDateTime scheduledArrivalLocal;
//    private  scheduledDuration;
    private String departureAirport;
    private String departureAirportName;
    private String departureCity;
    private String arrivalAirport;
    private String arrivalAirportName;
    private String arrivalCity;
    private String status;
    private String aircraftCode;
    private OffsetDateTime actualDeparture;
    private LocalDateTime actualDepartureLocal;
    private OffsetDateTime actualArrival;
    private LocalDateTime actualArrivalLocal;
//    private actual_duration;


    public FlightEntity() {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public OffsetDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(OffsetDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalDateTime getScheduledDepartureLocal() {
        return scheduledDepartureLocal;
    }

    public void setScheduledDepartureLocal(LocalDateTime scheduledDepartureLocal) {
        this.scheduledDepartureLocal = scheduledDepartureLocal;
    }

    public OffsetDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(OffsetDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public LocalDateTime getScheduledArrivalLocal() {
        return scheduledArrivalLocal;
    }

    public void setScheduledArrivalLocal(LocalDateTime scheduledArrivalLocal) {
        this.scheduledArrivalLocal = scheduledArrivalLocal;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public OffsetDateTime getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(OffsetDateTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public LocalDateTime getActualDepartureLocal() {
        return actualDepartureLocal;
    }

    public void setActualDepartureLocal(LocalDateTime actualDepartureLocal) {
        this.actualDepartureLocal = actualDepartureLocal;
    }

    public OffsetDateTime getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(OffsetDateTime actualArrival) {
        this.actualArrival = actualArrival;
    }

    public LocalDateTime getActualArrivalLocal() {
        return actualArrivalLocal;
    }

    public void setActualArrivalLocal(LocalDateTime actualArrivalLocal) {
        this.actualArrivalLocal = actualArrivalLocal;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "flightId=" + flightId +
                ", flightNo='" + flightNo + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", scheduledDepartureLocal=" + scheduledDepartureLocal +
                ", scheduledArrival=" + scheduledArrival +
                ", scheduledArrivalLocal=" + scheduledArrivalLocal +
                ", departureAirport='" + departureAirport + '\'' +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", status='" + status + '\'' +
                ", aircraftCode='" + aircraftCode + '\'' +
                ", actualDeparture=" + actualDeparture +
                ", actualDepartureLocal=" + actualDepartureLocal +
                ", actualArrival=" + actualArrival +
                ", actualArrivalLocal=" + actualArrivalLocal +
                '}';
    }
}
