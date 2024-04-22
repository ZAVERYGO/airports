package com.kozich.airports.service.impl;

import com.kozich.FilterDTO;
import com.kozich.airports.dao.api.FlightDao;
import com.kozich.airports.dao.entity.FlightEntity;
import com.kozich.airports.service.api.FlightService;
import com.kozich.airports.service.api.dto.FlightDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightDao dao;

    public FlightServiceImpl(FlightDao dao) {
        this.dao = dao;
    }

    @Override
    public FlightDTO get(int id) {
        return convert(dao.get(id).orElseThrow());
    }

    @Override
    public List<FlightDTO> get() {
        return dao.get().stream()
                .map(this::convert).
                collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO> get(Integer page, Integer size) {
        return dao.get(page, size).stream()
                .map(this::convert).
                collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO> getByFilter(FilterDTO filter, Integer page, Integer size) {
        List<FlightEntity> byFilter = dao.getByFilter(filter, page, size);
        List<FlightDTO> flightDTOS = new ArrayList<>();
        for (FlightEntity flightEntity : byFilter) {
            flightDTOS.add(convert(flightEntity));
        }
        return flightDTOS;
    }

    private FlightDTO convert(FlightEntity entity){
        return FlightDTO.builder()
                .flightId(entity.getFlightId())
                .flightNo(entity.getFlightNo())
                .scheduledDeparture(entity.getScheduledDeparture())
                .scheduledDepartureLocal(entity.getScheduledDepartureLocal())
                .scheduledArrival(entity.getScheduledArrival())
                .scheduledArrivalLocal(entity.getScheduledArrivalLocal())
                .departureAirport(entity.getDepartureAirport())
                .departureAirportName(entity.getDepartureAirportName())
                .departureCity(entity.getDepartureCity())
                .arrivalAirport(entity.getArrivalAirport())
                .arrivalAirportName(entity.getArrivalAirportName())
                .arrivalCity(entity.getArrivalCity())
                .status(entity.getStatus())
                .aircraftCode(entity.getAircraftCode())
                .actualDeparture(entity.getActualDeparture())
                .actualDepartureLocal(entity.getActualDepartureLocal())
                .actualArrival(entity.getActualArrival())
                .actualArrivalLocal(entity.getActualArrivalLocal())
                .build();
    }
}
