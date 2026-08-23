package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Department;
import com.sltc.smartcare.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    // Endpoint to create a new medical department
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            Department savedDepartment = departmentService.addDepartment(department);
            return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: Database constraint violation (e.g., Duplicate ID).", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not save the department.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve a list of all departments
    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch departments.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find a department using its unique ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") String id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch the department.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to assign a specific doctor as the head of a department
    @PutMapping("/{departmentId}/head-doctor/{doctorId}")
    public ResponseEntity<?> setHeadDoctor(@PathVariable("departmentId") String departmentId, @PathVariable("doctorId") String doctorId) {
        try {
            Department updatedDepartment = departmentService.setHeadDoctor(departmentId, doctorId);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not set the head doctor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}