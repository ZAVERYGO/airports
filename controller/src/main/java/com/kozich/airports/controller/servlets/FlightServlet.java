package com.kozich.airports.controller.servlets;

import com.kozich.airports.controller.factory.ControllerFactory;
import com.kozich.airports.service.api.FlightService;
import com.kozich.airports.service.api.dto.FlightDTO;
import com.kozich.airports.service.factory.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/flight")
public class FlightServlet extends HttpServlet {

    private static final String ID_PARAM = "id";
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
        String id = req.getParameter(ID_PARAM);

        PrintWriter writer = resp.getWriter();
        if(id != null && !id.isBlank()){
            writer.write(mapper.writeValueAsString(flightService.get(Integer.parseInt(id))));
            return;
        } else {
            String pageRaw = req.getParameter(PAGE_PARAM);
            String sizeRaw = req.getParameter(SIZE_PARAM);

            Integer page = pageRaw != null ? Integer.parseInt(pageRaw) : null;
            Integer size = sizeRaw != null ? Integer.parseInt(sizeRaw) : null;

            List<FlightDTO> data = flightService.get(page, size);

            writer.write(mapper.writeValueAsString(data));
            return;
        }
    }
}
