package com.sltc.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;

@Entity
@Table(name = "patients")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String bloodGroup;
    private String emergencyContact;

    public Patient(String name, LocalDate dob, String gender, String address, String contactNumber, Long patientId, String bloodGroup, String emergencyContact) {
        super(name, dob, gender, address, contactNumber);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
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