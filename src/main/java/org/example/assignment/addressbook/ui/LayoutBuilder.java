package org.example.assignment.addressbook.ui;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import org.example.assignment.addressbook.service.ContactService;

/**
 * Created by Anand Swarup V on 7/13/15.
 */
public class LayoutBuilder {

    public static VerticalLayout buildLayout(ContactService contactService) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setHeight(100.0f, Sizeable.Unit.PERCENTAGE);
        mainLayout.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        FormLayout contactForm = new ContactForm(contactService);
        mainLayout.addComponent(contactForm);
        mainLayout.setComponentAlignment(contactForm, Alignment.MIDDLE_CENTER);
        return mainLayout;
    }

}
