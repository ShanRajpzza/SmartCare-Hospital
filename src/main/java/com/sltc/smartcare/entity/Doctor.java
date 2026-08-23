package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "doctor")
// මෙන්න මේ කෑල්ල තමයි අලුතෙන් එකතු වෙන්නේ (Database එක වෙනස් නොකර ඉන්න)
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "Doctor_Name"))
})
public class Doctor extends Person {

    @Id
    @Column(name = "Doctor_ID", length = 20)
    private String doctorId;

    @Column(name = "Qualification", nullable = false, length = 50)
    private String qualification;

    @Column(name = "Specialization", nullable = false, length = 50)
    private String specialization;

    @Column(name = "Consultation_Fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal consultationFee;

    @ManyToOne
    @JoinColumn(name = "Department_ID")
    private Department department;

    public Doctor() {
        super();
    }

    // Person එකේ variables ටිකත් එක්ක Constructor එක
    public Doctor(String name, LocalDate dob, String gender, String address, String contactNumber,
                  String doctorId, String qualification, String specialization, BigDecimal consultationFee) {
        super(name, dob, gender, address, contactNumber);
        this.doctorId = doctorId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    // Doctor ට අදාළ Getters and Setters විතරයි තියෙන්නේ
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