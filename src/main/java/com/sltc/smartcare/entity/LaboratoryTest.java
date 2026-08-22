package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "laboratory_test")
public class LaboratoryTest {

    @Id
    @Column(name = "Lab_Test_ID")
    private String labTestId;

    @Column(name = "Test_Name")
    private String testName;

    @Column(name = "Test_Date")
    private LocalDate testDate;

    @Column(name = "Test_Result")
    private String testResult;

    @Column(name = "Test_Status")
    private String testStatus;

    @Column(name = "Technician_Name")
    private String technicianName;

    @Column(name = "Test_Charge")
    private BigDecimal testCharge;

    @Column(name = "Patient_ID")
    private String patientId;

    @Column(name = "Doctor_ID")
    private String doctorId;

    // Getters and Setters
    public String getLabTestId() { return labTestId; }
    public void setLabTestId(String labTestId) { this.labTestId = labTestId; }

    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }

    public LocalDate getTestDate() { return testDate; }
    public void setTestDate(LocalDate testDate) { this.testDate = testDate; }

    public String getTestResult() { return testResult; }
    public void setTestResult(String testResult) { this.testResult = testResult; }

    public String getTestStatus() { return testStatus; }
    public void setTestStatus(String testStatus) { this.testStatus = testStatus; }

    public String getTechnicianName() { return technicianName; }
    public void setTechnicianName(String technicianName) { this.technicianName = technicianName; }

    public BigDecimal getTestCharge() { return testCharge; }
    public void setTestCharge(BigDecimal testCharge) { this.testCharge = testCharge; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
}