package com.kozich;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterDTO {
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private LocalDateTime departureDateFrom;
    private LocalDateTime departureDateTo;
    private LocalDateTime arrivalDateFrom;
    private LocalDateTime arrivalDateTo;
}
