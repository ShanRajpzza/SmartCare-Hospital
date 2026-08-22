package com.sltc.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

// Parent class to demonstrate OOP Inheritance
@MappedSuperclass
public class Person {

    // Contact number field inherited by child classes
    @Column(name = "contact_number", nullable = false, length = 15)
    private String contactNumber;

    public Person() {}

    public Person(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}