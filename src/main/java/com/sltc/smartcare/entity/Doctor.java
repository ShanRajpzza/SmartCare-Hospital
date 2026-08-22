package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

// Doctor entity extending Person to inherit common attributes
@Entity
@Table(name = "doctor")
public class Doctor extends Person {

    @Id
    @Column(name = "Doctor_ID", length = 20)
    private String doctorId;

    @Column(name = "Doctor_Name", nullable = false, length = 100)
    private String doctorName;

    @Column(name = "Qualification", nullable = false, length = 50)
    private String qualification;

    @Column(name = "Specialization", nullable = false, length = 50)
    private String specialization;

    @Column(name = "Consultation_Fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal consultationFee;

    // Many to One relationship with Department
    @ManyToOne
    @JoinColumn(name = "Department_ID")
    private Department department;

    public Doctor() {}

    //Getters and setters
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public BigDecimal getConsultationFee() { return consultationFee; }
    public void setConsultationFee(BigDecimal consultationFee) { this.consultationFee = consultationFee; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}