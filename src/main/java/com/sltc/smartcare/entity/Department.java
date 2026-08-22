package com.sltc.smartcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

// Represents a medical department within the hospital
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "Department_ID", length = 20)
    private String departmentId;

    @Column(name = "Department_Name", nullable = false, length = 100)
    private String departmentName;

    @Column(name = "Location", length = 100)
    private String location;

    // @JsonIgnore prevents infinite recursion during JSON serialization (Loop වෙන එක නවත්වනවා)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Head_Doctor_ID")
    private Doctor headDoctor;

    public Department() {}

    public Department(String departmentId, String departmentName, String location) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
    }

    // Getters and Setters
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Doctor getHeadDoctor() { return headDoctor; }
    public void setHeadDoctor(Doctor headDoctor) { this.headDoctor = headDoctor; }
}