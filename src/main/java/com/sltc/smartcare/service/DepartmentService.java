package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Department;
import com.sltc.smartcare.entity.Doctor;
import com.sltc.smartcare.repository.DepartmentRepository;
import com.sltc.smartcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // Validates mandatory fields before saving
    private void validateDepartment(Department department) {
        if (department.getDepartmentId() == null || department.getDepartmentId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Department ID cannot be empty!");
        }
        if (department.getDepartmentName() == null || department.getDepartmentName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Department Name cannot be empty!");
        }
    }

    // Adds a new department with duplicate ID validation
    public Department addDepartment(Department department) {
        if (departmentRepository.existsById(department.getDepartmentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Department with ID " + department.getDepartmentId() + " already exists!");
        }
        validateDepartment(department);
        return departmentRepository.save(department);
    }

    // Retrieves all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Finds a specific department by ID
    public Department getDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Department not found with ID: " + departmentId));
    }

    // Assigns an existing doctor as the head of a department
    public Department setHeadDoctor(String departmentId, String doctorId) {
        Department department = getDepartmentById(departmentId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Doctor not found with ID: " + doctorId));

        department.setHeadDoctor(doctor);
        return departmentRepository.save(department);
    }
}