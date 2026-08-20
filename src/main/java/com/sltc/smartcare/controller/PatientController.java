package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Patient;
import com.sltc.smartcare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // 1. Register Patient (POST)
    @PostMapping
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // 2. View All Patients (GET)
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // 3. View Patient Details by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) { // Long -> String
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // 4. Update Patient Details (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @Valid @RequestBody Patient patient) { // Long -> String
        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updated);
    }

    // 5. Delete Patient Record (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable String id) { // Long -> String
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }

    // 6. Search Patients by Name (GET)
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam("name") String name) {
        return ResponseEntity.ok(patientService.searchPatientsByName(name));
    }
}