package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Admission;
import com.sltc.smartcare.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin(origins = "*")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    // API to admit patient
    @PostMapping("/admit")
    public ResponseEntity<?> admitPatient(@Valid @RequestBody Admission admission) {
        try {
            return ResponseEntity.ok(admissionService.admitPatient(admission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API to discharge patient and release room
    @PutMapping("/{id}/discharge")
    public ResponseEntity<?> dischargePatient(
            @PathVariable("id") String admissionId,
            @RequestParam("dischargeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dischargeDate) {
        try {
            return ResponseEntity.ok(admissionService.dischargePatient(admissionId, dischargeDate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API to fetch all admissions
    @GetMapping
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        return ResponseEntity.ok(admissionService.getAllAdmissions());
    }

    // API to fetch admissions by Patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Admission>> getAdmissionsByPatientId(@PathVariable("patientId") String patientId) {
        return ResponseEntity.ok(admissionService.getAdmissionsByPatientId(patientId));
    }
}
