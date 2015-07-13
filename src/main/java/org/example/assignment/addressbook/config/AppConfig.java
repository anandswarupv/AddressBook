package org.example.assignment.addressbook.config;

import org.example.assignment.addressbook.dao.ContactDao;
import org.example.assignment.addressbook.service.ContactService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Anand Swarup V on 7/12/15.
 */
@Configuration
public class AppConfig {

    @Bean(name="contactService")
    public ContactService getContactService() {
        return new ContactService();
    }

    @Bean(name="contactDao")
    public ContactDao getContactDao() {
        return new ContactDao();
    }

}