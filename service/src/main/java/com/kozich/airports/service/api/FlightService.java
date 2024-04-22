package com.kozich.airports.service.api;

import com.kozich.FilterDTO;
import com.kozich.airports.dao.entity.FlightEntity;
import com.kozich.airports.service.api.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO get(int id);
    List<FlightDTO> get();
    List<FlightDTO> get(Integer page, Integer size);
    List<FlightDTO> getByFilter(FilterDTO filter, Integer page, Integer size);
}
