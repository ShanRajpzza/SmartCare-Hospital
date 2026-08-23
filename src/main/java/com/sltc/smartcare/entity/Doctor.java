package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "doctor")
// Overriding the inherited 'name' attribute to map it to the existing 'Doctor_Name' database column
// to prevent database schema conflicts
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "Doctor_Name"))
})
// Primary Key for the Doctor entity
public class Doctor extends Person {

    @Id
    @Column(name = "Doctor_ID", length = 20)
    private String doctorId;
    // Educational qualifications of the doctor (e.g., MBBS, MD)
    @Column(name = "Qualification", nullable = false, length = 50)
    private String qualification;
    // Medical specialization or field of expertise (e.g., Cardiologist, Neurologist)
    @Column(name = "Specialization", nullable = false, length = 50)
    private String specialization;
    // The standard fee charged by the doctor for a consultation session
    @Column(name = "Consultation_Fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal consultationFee;
    // Many-to-One relationship: Many doctors can be assigned to a single department
    @ManyToOne
    @JoinColumn(name = "Department_ID")
    private Department department;

    public Doctor() {
        super();
    }


    public Doctor(String name, LocalDate dob, String gender, String address, String contactNumber,
                  String doctorId, String qualification, String specialization, BigDecimal consultationFee) {
        super(name, dob, gender, address, contactNumber);
        this.doctorId = doctorId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    // --- Getters and Setters for Doctor-specific attributes ---
    // Note: Inherited properties like getName() and getDob() are accessible via the parent class
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public BigDecimal getConsultationFee() { return consultationFee; }
    public void setConsultationFee(BigDecimal consultationFee) { this.consultationFee = consultationFee; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}