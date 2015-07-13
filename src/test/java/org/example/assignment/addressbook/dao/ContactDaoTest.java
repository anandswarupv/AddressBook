package org.example.assignment.addressbook.dao;

import org.example.assignment.addressbook.config.ConnectionManager;
import org.example.assignment.addressbook.model.Contact;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.*;

/**
 * Created by Anand Swarup V on 7/13/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionManager.class, ContactDao.class})
public class ContactDaoTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        try {
            connection = getConnection();
            createTables();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;TRACE_LEVEL_SYSTEM_OUT=1";
        String driver = "org.h2.Driver";
        String userName = "sa";
        String password = "password";
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url, userName, password);
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE CONTACT(name text,email text,profession text,school text,hospital text)");
        statement.close();
    }

    @Test
    public void shouldSaveContactToDatabase() throws Exception {
        PowerMockito.mockStatic(ConnectionManager.class);
        PowerMockito.when(ConnectionManager.getConnection()).thenReturn(connection);
        ContactDao contactDao = new ContactDao();
        Contact contact = new Contact("A", "B", "C", "D", null);
        contactDao.save(contact);
        Assert.assertEquals(1, validate(contact));
    }

    @Test
    public void shouldReturnFalseForSaveForClosedConnection() throws Exception {
        PowerMockito.mockStatic(ConnectionManager.class);
        connection.close();
        PowerMockito.when(ConnectionManager.getConnection()).thenReturn(connection);
        ContactDao contactDao = new ContactDao();
        Contact contact = new Contact("A", "B", "C", "D", null);
        Assert.assertFalse(contactDao.save(contact));
    }

    private int validate(Contact contact) throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) as COUNT from contact where name = '" + contact.getName() + "'");
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt("COUNT");
        }
        statement.close();
        connection.close();
        return count;
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE CONTACT");
        statement.close();
        connection.close();
    }

}