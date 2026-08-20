package com.sltc.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient extends Person {

    @Id
    @Column(name = "Patient_ID")
    private String patientId;

    @Column(name = "Blood_Group")
    private String bloodGroup;

    @Column(name = "Emergency_Contact")
    private String emergencyContact;


    public Patient() {
        super();
    }

    // Parameterized Constructor
    public Patient(String name, LocalDate dob, String gender, String address, String contactNumber,
                   String patientId, String bloodGroup, String emergencyContact) {
        super(name, dob, gender, address, contactNumber);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}