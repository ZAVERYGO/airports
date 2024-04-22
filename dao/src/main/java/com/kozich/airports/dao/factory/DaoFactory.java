package com.kozich.airports.dao.factory;

import com.kozich.airports.dao.api.FlightDao;
import com.kozich.airports.dao.impl.FlightDaoImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private final static String DB_URL_ENV_NAME = "AIRPORT_DB_URL";
    private final static String USER_DB_ENV_NAME = "AIRPORT_DB_USER";
    private final static String PASSWORD_DB_ENV_NAME = "AIRPORT_DB_PASSWORD";

    private static volatile FlightDao flightDao;

    private static DataSource ds;

    static {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
        }  catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        //cpds.setJdbcUrl( System.getenv(DB_URL_ENV_NAME) );
        //cpds.setUser(System.getenv(USER_DB_ENV_NAME));
        //cpds.setPassword(System.getenv(PASSWORD_DB_ENV_NAME));
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo");
        cpds.setUser("postgres");
        cpds.setPassword("123456");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        ds = cpds;
    }

    public static FlightDao getFlightDao(){
        if(flightDao == null){
            synchronized (DaoFactory.class){
                if(flightDao == null){
                    flightDao = new FlightDaoImpl();
                }
            }
        }
        return flightDao;
    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e){
            throw new IllegalStateException("Невозможно подключиться к базе данных", e);
        }
    }
}
