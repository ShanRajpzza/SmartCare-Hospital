package com.sltc.smartcare.entity;

import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
import java.time.LocalDate;

@MappedSuperclass
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public abstract class Person {

    private String name;
    private LocalDate dob;
    private String gender;
    private String address;
    private String contactNumber;

    public Person(String name, LocalDate dob, String gender, String address, String contactNumber) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
    }

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