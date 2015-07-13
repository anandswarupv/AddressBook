package org.example.assignment.addressbook.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import org.example.assignment.addressbook.model.Contact;
import org.example.assignment.addressbook.service.ContactService;
import org.springframework.util.StringUtils;

/**
 * Created by Anand Swarup V on 7/13/15.
 */
public class ContactForm extends FormLayout {

    private TextField name;
    private TextField email;
    private NativeSelect professionDropDown;
    private TextField school;
    private TextField hospital;
    private ContactService contactService;

    public ContactForm(ContactService contactService) {
        if (contactService == null) {
            throw new NullPointerException("ContactService is NULL");
        }
        this.contactService = contactService;
        styleForm();
        addHeader();
        addNameField();
        addEmailField();
        addProfessionFields();
        addSubmitButton();
    }

    private void styleForm() {
        setWidth(60.0f, Unit.PERCENTAGE);
        setHeight(60.0f, Unit.PERCENTAGE);
        setSpacing(true);
    }

    private void addHeader() {
        final Label header = new Label("Enter Contact Information");
        addComponent(header);
    }

    private void addSubmitButton() {
        Button submit = new Button("Submit");
        addComponent(submit);

        submit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        Contact contact = getContact();
        if (contact != null && contactService.saveContact(contact)) {
            Notification.show("Contact Saved!");
        } else if (contact == null){
            Notification.show("All fields are mandatory, please complete all fields");
        } else {
            Notification.show("Contact could not be saved!. Contact the administrator or try again later");
        }
    }

    private Contact getContact() {
        if (isValid(name.getValue()) && isValid(email.getValue()) && isValid(professionDropDown.getValue().toString())
                && (isValid(school.getValue()) || isValid(hospital.getValue()))) {
            return new Contact(
                    name.getValue(),
                    email.getValue(),
                    professionDropDown.getValue().toString(),
                    school.getValue(),
                    hospital.getValue());
        }
        return null;

    }

    private boolean isValid(String value) {
        return value != null && !StringUtils.isEmpty(value);
    }

    private void addProfessionFields() {
        professionDropDown = new NativeSelect("Profession");
        professionDropDown.addItem("Student");
        professionDropDown.addItem("Doctor");
        professionDropDown.setNullSelectionAllowed(false);
        professionDropDown.setValue("Student");
        professionDropDown.setImmediate(true);

        addComponent(professionDropDown);

        school = new TextField("School", "");
        school.setWidth(40.0f, Unit.PERCENTAGE);
        addComponent(school);

        hospital = new TextField("Hospital", "");
        hospital.setWidth(40.0f, Unit.PERCENTAGE);

        professionDropDown.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                String profession = valueChangeEvent.getProperty().getValue().toString();
                removeComponent(hospital);
                removeComponent(school);
                switch (profession) {
                    case "Student":
                        addComponent(school, 4);
                        break;
                    case "Doctor":
                        addComponent(hospital, 4);
                }
            }
        });
    }

    private void addEmailField() {
        email = new TextField("Email", "");
        email.setWidth(40.0f, Unit.PERCENTAGE);
        addComponent(email);
    }

    private void addNameField() {
        name = new TextField("Name", "");
        name.setWidth(40.0f, Unit.PERCENTAGE);
        addComponent(name);
    }
}
