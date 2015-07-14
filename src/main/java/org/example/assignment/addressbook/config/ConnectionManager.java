package org.example.assignment.addressbook.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Anand Swarup V on 7/13/15.
 */
public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String url = "jdbc:postgresql://localhost:5432/addressbook";
    private static final String user = "address";
    private static final String password = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return connection;
    }
}
