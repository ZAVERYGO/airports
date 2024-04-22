package com.kozich.airports.dao.api;

import com.kozich.FilterDTO;
import com.kozich.airports.dao.entity.FlightEntity;

import java.util.List;
import java.util.Optional;

public interface FlightDao {
    Optional<FlightEntity> get(int id);
    List<FlightEntity> get();
    List<FlightEntity> get(Integer page, Integer size);
    List<FlightEntity> getByFilter(FilterDTO filter, Integer page, Integer size);
}
