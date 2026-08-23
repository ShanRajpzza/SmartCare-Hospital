package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.TreatmentMedicine;
import com.sltc.smartcare.entity.TreatmentMedicineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentMedicineRepository extends JpaRepository<TreatmentMedicine, TreatmentMedicineId> {
    List<TreatmentMedicine> findByTreatmentId(String treatmentId);
}