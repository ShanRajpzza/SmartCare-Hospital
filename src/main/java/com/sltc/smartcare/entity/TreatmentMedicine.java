package com.sltc.smartcare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "treatment_medicine")
@IdClass(TreatmentMedicineId.class)
public class TreatmentMedicine {

    @Id
    @Column(name = "Treatment_ID")
    private String treatmentId;

    @Id
    @Column(name = "Medicine_ID")
    private String medicineId;


    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }
}