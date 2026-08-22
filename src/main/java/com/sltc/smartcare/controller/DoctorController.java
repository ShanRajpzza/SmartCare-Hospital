package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Doctor;
import com.sltc.smartcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Endpoint to register a new doctor
    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = doctorService.addDoctor(doctor);
            return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: Database constraint violation.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not save the doctor details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve all doctors
    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch doctors.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve a specific doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable("id") String doctorId) {
        try {
            Doctor doctor = doctorService.getDoctorById(doctorId);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch the doctor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to update doctor information
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("id") String doctorId, @RequestBody Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorService.updateDoctor(doctorId, doctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not update the doctor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to delete a doctor completely
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") String doctorId) {
        try {
            doctorService.deleteDoctor(doctorId);
            return new ResponseEntity<>("Doctor deleted successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not delete the doctor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to search for a doctor using a name
    @GetMapping("/search")
    public ResponseEntity<?> searchDoctors(@RequestParam("name") String name) {
        try {
            List<Doctor> doctors = doctorService.searchDoctorsByName(name);
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not search for doctors.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to link a doctor with a department
    @PutMapping("/{doctorId}/assign-department/{departmentId}")
    public ResponseEntity<?> assignDepartment(
            @PathVariable("doctorId") String doctorId,
            @PathVariable("departmentId") String departmentId) {
        try {
            Doctor updatedDoctor = doctorService.assignDepartment(doctorId, departmentId);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not assign department.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}