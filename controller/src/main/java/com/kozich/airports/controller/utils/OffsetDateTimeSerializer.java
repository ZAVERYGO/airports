package com.kozich.airports.controller.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {

    public OffsetDateTimeSerializer() {
        super(OffsetDateTime.class);
    }
    @Override
    public void serialize(OffsetDateTime value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeNumber(value.toInstant().toEpochMilli());
    }
}
