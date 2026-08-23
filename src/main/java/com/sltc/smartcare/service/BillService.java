package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Bill;
import com.sltc.smartcare.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    // Create bill with validation rule
    public Bill createBill(Bill bill) {
        // Business Rule Validation: Amount cannot be negative
        if (bill.getTotalAmount() != null && bill.getTotalAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bill total amount cannot be negative.");
        }
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public List<Bill> getBillsByPatientId(String patientId) {
        return billRepository.findByPatientId(patientId);
    }

    // Update payment details
    public Bill updatePaymentStatus(String billId, String paymentStatus, String paymentMethod) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + billId));

        bill.setPaymentStatus(paymentStatus);
        bill.setPaymentMethod(paymentMethod);
        return billRepository.save(bill);
    }
}