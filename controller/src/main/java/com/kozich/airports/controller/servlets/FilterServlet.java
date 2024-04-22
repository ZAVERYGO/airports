package com.kozich.airports.controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.FilterDTO;
import com.kozich.airports.controller.factory.ControllerFactory;
import com.kozich.airports.service.api.FlightService;
import com.kozich.airports.service.api.dto.FlightDTO;
import com.kozich.airports.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/api/flight/filter")
public class FilterServlet extends HttpServlet {
    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String DEPARTURE_AIRPORT_PARAM = "departureAirport";
    private static final String ARRIVAL_AIRPORT_PARAM = "arrivalAirport";
    private static final String STATUS_PARAM = "status";
    private static final String DEPARTURE_DATE_FROM_PARAM = "departureDateFrom";
    private static final String DEPARTURE_DATE_TO_PARAM = "departureDateTo";
    private static final String ARRIVAL_DATE_FROM_PARAM = "arrivalDateFrom";
    private static final String ARRIVAL_DATE_TO_PARAM = "arrivalDateTo";

    private final ObjectMapper mapper = ControllerFactory.getMapper();
    private final FlightService flightService = ServiceFactory.getFlightService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pageRaw = req.getParameter(PAGE_PARAM);
        String sizeRaw = req.getParameter(SIZE_PARAM);
        String departureAirport = req.getParameter(DEPARTURE_AIRPORT_PARAM);
        String arrivalAirport = req.getParameter(ARRIVAL_AIRPORT_PARAM);
        String status = req.getParameter(STATUS_PARAM);
        LocalDateTime departureDateFrom = LocalDateTime.parse(req.getParameter(DEPARTURE_DATE_FROM_PARAM));
        LocalDateTime departureDateTo = LocalDateTime.parse(req.getParameter(DEPARTURE_DATE_TO_PARAM));
        LocalDateTime arrivalDateFrom = LocalDateTime.parse(req.getParameter(ARRIVAL_DATE_FROM_PARAM));
        LocalDateTime arrivalDateTo = LocalDateTime.parse(req.getParameter(ARRIVAL_DATE_TO_PARAM));

        FilterDTO filter = new FilterDTO()
                .setDepartureAirport(departureAirport)
            .setArrivalAirport(arrivalAirport)
            .setStatus(status)
            .setDepartureDateFrom(departureDateFrom)
            .setDepartureDateTo(departureDateTo)
            .setArrivalDateFrom(arrivalDateFrom)
            .setArrivalDateTo(arrivalDateTo);

        Integer page = pageRaw != null ? Integer.parseInt(pageRaw) : null;
        Integer size = sizeRaw != null ? Integer.parseInt(sizeRaw) : null;

        List<FlightDTO> flights = flightService.getByFilter(filter, page, size);
        resp.getWriter().write(mapper.writeValueAsString(flights));

    }
}
