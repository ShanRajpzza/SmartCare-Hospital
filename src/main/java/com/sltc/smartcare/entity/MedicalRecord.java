package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "treatment")
public class MedicalRecord {

    @Id
    @Column(name = "Treatment_ID")
    private String treatmentId;

    @Column(name = "Patient_ID")
    private String patientId;

    @Column(name = "Doctor_ID")
    private String doctorId;

    @Column(name = "Diagnosis")
    private String diagnosis;

    @Column(name = "Prescription")
    private String prescription;

    @Column(name = "Treatment_Date")
    private LocalDate treatmentDate;

    @Column(name = "Treatment_Notes")
    private String treatmentNotes;

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentNotes() {
        return treatmentNotes;
    }

    public void setTreatmentNotes(String treatmentNotes) {
        this.treatmentNotes = treatmentNotes;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}