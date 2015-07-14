package org.example.assignment.addressbook.service;

import org.example.assignment.addressbook.dao.ContactDao;
import org.example.assignment.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Backend service class. This is just a typical Java backend implementation
 * class and nothing Vaadin specific.
 */
public class ContactService implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    ContactDao contactDao;

    public boolean saveContact(Contact contact) {
        if (contact != null) {
            return contactDao.save(contact);
        }
        LOGGER.error("Contact is NULL");
        throw new NullPointerException("Contact cannot be NULL");
    }
}
