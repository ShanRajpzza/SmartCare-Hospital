package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Bill;
import com.sltc.smartcare.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillController {

    @Autowired
    private BillService billService;

    // API to generate a new bill
    @PostMapping
    public ResponseEntity<?> createBill(@Valid @RequestBody Bill bill, BindingResult bindingResult) {
        // 1. Catch Validation Errors (@PositiveOrZero, @NotNull, etc.)
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // 2. Catch Service & Business Logic Exceptions
        try {
            Bill createdBill = billService.createBill(bill);
            return ResponseEntity.ok(createdBill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    // API to fetch all bills
    @GetMapping
    public ResponseEntity<?> getAllBills() {
        try {
            List<Bill> bills = billService.getAllBills();
            return ResponseEntity.ok(bills);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving bills: " + e.getMessage());
        }
    }

    // API to fetch bills for a specific patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getBillsByPatientId(@PathVariable("patientId") String patientId) {
        try {
            List<Bill> bills = billService.getBillsByPatientId(patientId);
            return ResponseEntity.ok(bills);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving bills for patient: " + e.getMessage());
        }
    }

    // API to update payment status and method
    @PutMapping("/{id}/pay")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable("id") String billId,
            @RequestParam("status") String paymentStatus,
            @RequestParam("method") String paymentMethod) {
        try {
            Bill updatedBill = billService.updatePaymentStatus(billId, paymentStatus, paymentMethod);
            return ResponseEntity.ok(updatedBill);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating payment status: " + e.getMessage());
        }
    }
}