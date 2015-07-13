package com.narvar.assignment.addressbook.service;

import com.narvar.assignment.addressbook.dao.ContactDao;
import com.narvar.assignment.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Backend service class. This is just a typical Java backend implementation
 * class and nothing Vaadin specific.
 */
public class ContactService {

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
