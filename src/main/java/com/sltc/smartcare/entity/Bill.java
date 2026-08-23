package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "Bill_ID", length = 20)
    private String billId;

    @NotNull(message = "Bill date is required")
    @Column(name = "Bill_Date", nullable = false)
    private LocalDate billDate;

    @Column(name = "Payment_Method")
    private String paymentMethod;

    @Column(name = "Payment_Status")
    private String paymentStatus;

    @PositiveOrZero(message = "Total amount cannot be negative")
    @Column(name = "Total_Amount")
    private BigDecimal totalAmount;

    @NotNull(message = "Patient ID is required")
    @Column(name = "Patient_ID", length = 20, nullable = false)
    private String patientId;

    public Bill() {
    }

    public Bill(String billId, LocalDate billDate, String paymentMethod,
                String paymentStatus, BigDecimal totalAmount, String patientId) {
        this.billId = billId;
        this.billDate = billDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.patientId = patientId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}