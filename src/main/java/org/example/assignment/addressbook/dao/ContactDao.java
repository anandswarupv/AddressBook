package org.example.assignment.addressbook.dao;

import org.example.assignment.addressbook.config.ConnectionManager;
import org.example.assignment.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Anand Swarup V on 7/12/15.
 */
public class ContactDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDao.class);
    private static final String insertIntoContact = "INSERT INTO contact(name, email, profession, school, hospital) VALUES (?, ?, ?, ?, ?);";

    public boolean save(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(insertIntoContact);

                preparedStatement.setString(1, contact.getName());
                preparedStatement.setString(2, contact.getEmail());
                preparedStatement.setString(3, contact.getProfession());
                preparedStatement.setString(4, contact.getSchool());
                preparedStatement.setString(5, contact.getHospital());

                preparedStatement.executeUpdate();
                LOGGER.info("Saved : {}", contact);
                return true;
            } else {
                LOGGER.error("Connection with the Database cannot be established");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }
}
