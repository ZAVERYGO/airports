package com.kozich.airports.service.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightDTO {
    private Integer flightId;
    private String flightNo;
    @JsonProperty("ScHeDuLe")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private OffsetDateTime scheduledDeparture;
    private LocalDateTime scheduledDepartureLocal;
    private OffsetDateTime scheduledArrival;
    private LocalDateTime scheduledArrivalLocal;
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
}
