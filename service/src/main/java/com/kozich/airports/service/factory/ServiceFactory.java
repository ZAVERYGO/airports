package com.kozich.airports.service.factory;

import com.kozich.airports.dao.factory.DaoFactory;
import com.kozich.airports.service.impl.FlightServiceImpl;
import com.kozich.airports.service.api.FlightService;

public class ServiceFactory {

    private static volatile FlightService flightService;

    public static FlightService getFlightService(){
        if(flightService == null){
            synchronized (ServiceFactory.class){
                if(flightService == null){
                    flightService = new FlightServiceImpl(DaoFactory.getFlightDao());
                }
            }
        }
        return flightService;
    }
}
