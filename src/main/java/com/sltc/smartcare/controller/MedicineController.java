package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Medicine;
import com.sltc.smartcare.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // Create a new record
    @PostMapping
    public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine) {
        try {
            Medicine savedMedicine = medicineService.createMedicine(medicine);
            return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while saving the medicine.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All
    @GetMapping
    public ResponseEntity<?> getAllMedicines() {
        try {
            List<Medicine> medicines = medicineService.getAllMedicines();
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch medicines.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicineById(@PathVariable String id) {
        try {
            Medicine medicine = medicineService.getMedicineById(id);
            return new ResponseEntity<>(medicine, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not fetch the medicine.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable String id, @RequestBody Medicine updatedMedicine) {
        try {
            Medicine medicine = medicineService.updateMedicine(id, updatedMedicine);
            return new ResponseEntity<>(medicine, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while updating the medicine.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable String id) {
        try {
            medicineService.deleteMedicine(id);
            return new ResponseEntity<>("Medicine deleted successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            // යම්කිසි ලෙඩෙක්ට මේ බෙහෙත දීලා තියෙනවා නම්, මේක මකන්න බැරි වෙන්න මේක දාලා තියෙන්නේ
            return new ResponseEntity<>("Error: Cannot delete this medicine because it is currently used in a treatment record.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Could not delete the medicine.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}