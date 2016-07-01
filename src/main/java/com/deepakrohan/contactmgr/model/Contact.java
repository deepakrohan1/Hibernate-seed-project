package com.deepakrohan.contactmgr.model;

import javax.persistence.*;

/**
 * Created by rohan on 7/1/16.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    public Contact() {
    }

    public Contact(ContactBuilder contactBuilder) {
        this.firstName = contactBuilder.firstName;
        this.lastName = contactBuilder.lastName;
        this.email = contactBuilder.email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class ContactBuilder{
        private String firstName;
        private String lastName;
        private String email;

        public ContactBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public  ContactBuilder addEmail(String email){
            this.email = email;
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }
    }
}
