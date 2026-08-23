package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.MedicalRecord;
import com.sltc.smartcare.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Create a new record
    @PostMapping
    public ResponseEntity<?> createRecord(@RequestBody MedicalRecord record) {
        try {
            MedicalRecord savedRecord = medicalRecordService.createMedicalRecord(record);
            return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            // Validation Errors and Duplicate ID Errors
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: The Patient ID or Doctor ID does not exist in the database.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while saving.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All
    @GetMapping
    public ResponseEntity<?> getAllRecords() {
        try {
            List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch records.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable String id) {
        try {
            MedicalRecord record = medicalRecordService.getMedicalRecordById(id);
            return new ResponseEntity<>(record, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch the record.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable String id, @RequestBody MedicalRecord updatedRecord) {
        try {
            MedicalRecord record = medicalRecordService.updateMedicalRecord(id, updatedRecord);
            return new ResponseEntity<>(record, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // Validation Errors
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: The Patient ID or Doctor ID does not exist.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while updating.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable String id) {
        try {
            medicalRecordService.deleteMedicalRecord(id);
            return new ResponseEntity<>("Medical Record deleted successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not delete the record.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}