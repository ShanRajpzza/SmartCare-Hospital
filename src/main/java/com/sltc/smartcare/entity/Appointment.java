package com.sltc.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @Column(name = "Appointment_ID")
    private String appointmentId;

    @Column(name = "Appointment_Date")
    private LocalDate appointmentDate;

    @Column(name = "Appointment_Time")
    private LocalTime appointmentTime;

    @Column(name = "Consultation_Room")
    private String consultationRoom;

    @Column(name = "Appointment_Status")
    private String appointmentStatus;

    @Column(name = "Patient_ID")
    private String patientId;

    @Column(name = "Doctor_ID")
    private String doctorId;

    // Default Constructor
    public Appointment() {}

    // Encapsulation: Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public LocalTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getConsultationRoom() { return consultationRoom; }
    public void setConsultationRoom(String consultationRoom) { this.consultationRoom = consultationRoom; }

    public String getAppointmentStatus() { return appointmentStatus; }
    public void setAppointmentStatus(String appointmentStatus) { this.appointmentStatus = appointmentStatus; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
}