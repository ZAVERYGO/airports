package com.kozich.airports.dao.impl;

import com.kozich.FilterDTO;
import com.kozich.airports.dao.entity.FlightEntity;
import com.kozich.airports.dao.api.FlightDao;
import com.kozich.airports.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDaoImpl implements FlightDao {

    private final static String GET_BY_ID = "SELECT\n" +
            "    flight_id,\n" +
            "    flight_no,\n" +
            "    scheduled_departure,\n" +
            "    scheduled_departure_local,\n" +
            "    scheduled_arrival,\n" +
            "    scheduled_arrival_local,\n" +
            "    scheduled_duration,\n" +
            "    departure_airport,\n" +
            "    departure_airport_name,\n" +
            "    departure_city,\n" +
            "    arrival_airport,\n" +
            "    arrival_airport_name,\n" +
            "    arrival_city,\n" +
            "    status,\n" +
            "    aircraft_code,\n" +
            "    actual_departure,\n" +
            "    actual_departure_local,\n" +
            "    actual_arrival,\n" +
            "    actual_arrival_local,\n" +
            "    actual_duration\n" +
            "FROM\n" +
            "    bookings.flights_v\n" +
            "WHERE flight_id = ?;\n";

    private final static String GET_ALL = "SELECT\n" +
            "    flight_id,\n" +
            "    flight_no,\n" +
            "    scheduled_departure,\n" +
            "    scheduled_departure_local,\n" +
            "    scheduled_arrival,\n" +
            "    scheduled_arrival_local,\n" +
            "    scheduled_duration,\n" +
            "    departure_airport,\n" +
            "    departure_airport_name,\n" +
            "    departure_city,\n" +
            "    arrival_airport,\n" +
            "    arrival_airport_name,\n" +
            "    arrival_city,\n" +
            "    status,\n" +
            "    aircraft_code,\n" +
            "    actual_departure,\n" +
            "    actual_departure_local,\n" +
            "    actual_arrival,\n" +
            "    actual_arrival_local,\n" +
            "    actual_duration\n" +
            "FROM\n" +
            "    bookings.flights_v;\n";

    private final static String GET_PAGE = "SELECT\n" +
            "    flight_id,\n" +
            "    flight_no,\n" +
            "    scheduled_departure,\n" +
            "    scheduled_departure_local,\n" +
            "    scheduled_arrival,\n" +
            "    scheduled_arrival_local,\n" +
            "    scheduled_duration,\n" +
            "    departure_airport,\n" +
            "    departure_airport_name,\n" +
            "    departure_city,\n" +
            "    arrival_airport,\n" +
            "    arrival_airport_name,\n" +
            "    arrival_city,\n" +
            "    status,\n" +
            "    aircraft_code,\n" +
            "    actual_departure,\n" +
            "    actual_departure_local,\n" +
            "    actual_arrival,\n" +
            "    actual_arrival_local,\n" +
            "    actual_duration\n" +
            "FROM\n" +
            "    bookings.flights_v\n" +
            "ORDER BY scheduled_departure\n" +
            "LIMIT ? OFFSET ?;\n";

    private final static String GET_FILTER_PAGE = "SELECT *\n" +
            "FROM bookings.flights_v\n" +
            "WHERE \n" +
            "(departure_airport = ? OR ? IS NULL) AND \n" +
            "(arrival_airport = ? OR ? IS NULL) AND \n" +
            "(status = ? OR ? IS NULL) AND \n" +
            "(scheduled_departure >= ? OR ? IS NULL) AND \n" +
            "(scheduled_departure <= ? OR ? IS NULL) AND \n" +
            "(scheduled_arrival >= ? OR ? IS NULL) AND \n" +
            "(scheduled_arrival <= ? OR ? IS NULL) \n" +
            "ORDER BY scheduled_departure\n" +
            "LIMIT ? OFFSET ?;";
    @Override
    public Optional<FlightEntity> get(int id) {
        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_BY_ID)){
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()){
                while (rs.next()){
                    return Optional.of(read(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightEntity> get() {
        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL);
            ResultSet rs = st.executeQuery()){
            List<FlightEntity> data = new ArrayList<>();
            while (rs.next()){
                data.add(read(rs));
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightEntity> get(Integer page, Integer size) {
        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_PAGE)){

            st.setLong(1, size);
            st.setLong(2, (page - 1L) * size);

            try(ResultSet rs = st.executeQuery()){
                List<FlightEntity> data = new ArrayList<>();
                while (rs.next()){
                    data.add(read(rs));
                }
                return data;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightEntity> getByFilter(FilterDTO filter, Integer page, Integer size) {
        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_FILTER_PAGE)){

            st.setObject(1, filter.getDepartureAirport());
            st.setObject(2, filter.getDepartureAirport());
            st.setObject(3, filter.getArrivalAirport());
            st.setObject(4, filter.getArrivalAirport());
            st.setObject(5, filter.getStatus());
            st.setObject(6, filter.getStatus());
            st.setObject(7, filter.getDepartureDateFrom());
            st.setObject(8, filter.getDepartureDateFrom());
            st.setObject(9, filter.getDepartureDateTo());
            st.setObject(10, filter.getDepartureDateTo());
            st.setObject(11, filter.getArrivalDateFrom());
            st.setObject(12, filter.getArrivalDateFrom());
            st.setObject(13, filter.getArrivalDateTo());
            st.setObject(14, filter.getArrivalDateTo());
            st.setObject(15, page);
            st.setObject(16, size);

            try(ResultSet rs = st.executeQuery()){
                List<FlightEntity> data = new ArrayList<>();
                while (rs.next()){
                    data.add(read(rs));
                }
                return data;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private FlightEntity read(ResultSet rs) throws SQLException {
        FlightEntity entity = new FlightEntity();
        entity.setFlightId(rs.getInt("flight_id"));
        entity.setFlightNo(rs.getString("flight_no"));
        entity.setScheduledDeparture(rs.getObject("scheduled_departure", OffsetDateTime.class));
        entity.setScheduledDepartureLocal(rs.getObject("scheduled_departure_local", LocalDateTime.class));
        entity.setScheduledArrival(rs.getObject("scheduled_arrival", OffsetDateTime.class));
        entity.setScheduledArrivalLocal(rs.getObject("scheduled_arrival_local", LocalDateTime.class));
        entity.setDepartureAirport(rs.getString("departure_airport"));
        entity.setDepartureAirportName(rs.getString("departure_airport_name"));
        entity.setDepartureCity(rs.getString("departure_city"));
        entity.setArrivalAirport(rs.getString("arrival_airport"));
        entity.setArrivalAirportName(rs.getString("arrival_airport_name"));
        entity.setArrivalCity(rs.getString("arrival_city"));
        entity.setStatus(rs.getString("status"));
        entity.setAircraftCode(rs.getString("aircraft_code"));
        entity.setActualDeparture(rs.getObject("actual_departure", OffsetDateTime.class));
        entity.setActualDepartureLocal(rs.getObject("actual_departure_local", LocalDateTime.class));
        entity.setActualArrival(rs.getObject("actual_arrival", OffsetDateTime.class));
        entity.setActualArrivalLocal(rs.getObject("actual_arrival_local", LocalDateTime.class));
        return entity;
    }
}
