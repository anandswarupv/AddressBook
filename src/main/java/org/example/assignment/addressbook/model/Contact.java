package org.example.assignment.addressbook.model;

/**
 * A simple DTO for the contacts example.
 *
 */
public class Contact {

    private final String name;
    private final String email;
    private final String profession;
    private final String school;
    private final String hospital;

    public Contact(String name, String email, String profession, String school, String hospital) {
        this.name = name;
        this.email = email;
        this.profession = profession;
        this.school = school;
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    public String getSchool() {
        return school;
    }

    public String getHospital() {
        return hospital;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profession='" + profession + '\'' +
                ", school='" + school + '\'' +
                ", hospital='" + hospital + '\'' +
                '}';
    }
}
