package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "admission")
public class Admission {

    @Id
    @Column(name = "Admission_ID", length = 20)
    private String admissionId;

    @NotNull(message = "Admission date is required")
    @Column(name = "Admission_Date", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "Discharge_Date")
    private LocalDate dischargeDate;

    @NotNull(message = "Bed number is required")
    @Column(name = "Bed_Number", length = 10, nullable = false)
    private String bedNumber;

    @Column(name = "Admission_Status")
    private String admissionStatus = "Admitted"; // Admitted, Discharged,Cancelled

    @NotNull(message = "Patient ID is required")
    @Column(name = "Patient_ID", length = 20, nullable = false)
    private String patientId;

    // Association: Many Admissions belong to One Room
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Room_ID", nullable = false)
    private Room room;

    // Default Constructor
    public Admission() {
    }

    // Parameterized Constructor
    public Admission(String admissionId, LocalDate admissionDate, LocalDate dischargeDate,
                     String bedNumber, String admissionStatus, String patientId, Room room) {
        this.admissionId = admissionId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.bedNumber = bedNumber;
        this.admissionStatus = admissionStatus;
        this.patientId = patientId;
        this.room = room;
    }

    // Getters and Setters (Encapsulation)
    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(String admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}