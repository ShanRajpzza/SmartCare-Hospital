package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.TreatmentMedicine;
import com.sltc.smartcare.service.TreatmentMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions") // API URL
public class TreatmentMedicineController {

    @Autowired
    private TreatmentMedicineService service;

    // Create a new record
    @PostMapping
    public ResponseEntity<?> assignMedicine(@RequestBody TreatmentMedicine record) {
        try {
            TreatmentMedicine savedRecord = service.assignMedicine(record);
            return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            // නැති Treatment එකක් හරි, නැති බෙහෙතක් හරි දුන්නොත් මේකෙන් අල්ලනවා
            return new ResponseEntity<>("Error: The specified Treatment ID or Medicine ID does not exist in the database.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read
    @GetMapping("/treatment/{treatmentId}")
    public ResponseEntity<?> getMedicinesForTreatment(@PathVariable String treatmentId) {
        try {
            List<TreatmentMedicine> medicines = service.getMedicinesForTreatment(treatmentId);
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch medicines for this treatment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    @DeleteMapping("/{treatmentId}/{medicineId}")
    public ResponseEntity<?> removeMedicine(@PathVariable String treatmentId, @PathVariable String medicineId) {
        try {
            service.removeMedicineFromTreatment(treatmentId, medicineId);
            return new ResponseEntity<>("Medicine removed from treatment successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not remove the medicine.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}