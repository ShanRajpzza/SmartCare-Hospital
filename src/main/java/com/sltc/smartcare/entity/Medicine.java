package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @Column(name = "Medicine_ID")
    private String medicineId;

    @Column(name = "Medicine_Name")
    private String name;

    @Column(name = "Unit_Price")
    private BigDecimal price;

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}