package com.sltc.smartcare.entity;

import java.io.Serializable;
import java.util.Objects;

public class TreatmentMedicineId implements Serializable {

    private String treatmentId;
    private String medicineId;

    // Default Constructor
    public TreatmentMedicineId() {
    }

    public TreatmentMedicineId(String treatmentId, String medicineId) {
        this.treatmentId = treatmentId;
        this.medicineId = medicineId;
    }

    // Getters and Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentMedicineId that = (TreatmentMedicineId) o;
        return Objects.equals(treatmentId, that.treatmentId) &&
                Objects.equals(medicineId, that.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatmentId, medicineId);
    }
}