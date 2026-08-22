package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.LaboratoryTest;
import com.sltc.smartcare.service.LaboratoryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/lab-tests") // API URL here
public class LaboratoryTestController {

    @Autowired
    private LaboratoryTestService service;
    // Create a new record
    @PostMapping
    public ResponseEntity<?> createLabTest(@RequestBody LaboratoryTest test) {
        try {
            LaboratoryTest savedTest = service.createLabTest(test);
            return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: The specified Patient ID or Doctor ID does not exist in the database.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while saving the lab test.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All
    @GetMapping
    public ResponseEntity<?> getAllLabTests() {
        try {
            List<LaboratoryTest> tests = service.getAllLabTests();
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch lab tests.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getLabTestById(@PathVariable String id) {
        try {
            LaboratoryTest test = service.getLabTestById(id);
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch the lab test.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLabTest(@PathVariable String id, @RequestBody LaboratoryTest updatedTest) {
        try {
            LaboratoryTest test = service.updateLabTest(id, updatedTest);
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: The specified Patient ID or Doctor ID does not exist.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while updating the lab test.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLabTest(@PathVariable String id) {
        try {
            service.deleteLabTest(id);
            return new ResponseEntity<>("Lab Test deleted successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not delete the lab test.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}