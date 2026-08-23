package com.sltc.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Person {

    @Column(name = "Full_Name")
    private String name;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Address")
    private String address;

    @Column(name = "Contact_Number")
    private String contactNumber;

    // Hibernate/JPA සඳහා අත්‍යවශ්‍ය Default (No-Args) Constructor එක
    public Person() {
    }

    // Parameterized Constructor
    public Person(String name, LocalDate dob, String gender, String address, String contactNumber) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}