package com.kozich.airports.controller.factory;

import com.kozich.airports.controller.utils.LocalDateTimeSerializer;
import com.kozich.airports.controller.utils.OffsetDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class ControllerFactory {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
//        mapper.registerModule(new JavaTimeModule());

        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
        mapper.registerModule(module);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
