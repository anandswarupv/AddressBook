package org.example.assignment.addressbook.service;

import org.example.assignment.addressbook.dao.ContactDao;
import org.example.assignment.addressbook.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Anand Swarup V on 7/13/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    ContactDao contactDao;

    @InjectMocks
    ContactService contactService;

    @Test
    public void shouldSaveContact() throws Exception {
        Contact contact = new Contact("John", "john@email.com", "Student", "John School of ABC", null);
        Mockito.when(contactDao.save(contact)).thenReturn(true);
        Assert.assertTrue(contactService.saveContact(contact));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEForNullContact() throws Exception {
        contactService.saveContact(null);
    }

}