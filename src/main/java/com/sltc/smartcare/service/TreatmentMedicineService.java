package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.TreatmentMedicine;
import com.sltc.smartcare.entity.TreatmentMedicineId;
import com.sltc.smartcare.repository.TreatmentMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TreatmentMedicineService {

    @Autowired
    private TreatmentMedicineRepository repository;

    private void validateRecord(TreatmentMedicine record) {
        if (record.getTreatmentId() == null || record.getTreatmentId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Treatment ID cannot be empty!");
        }
        if (record.getMedicineId() == null || record.getMedicineId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Medicine ID cannot be empty!");
        }
    }

    // new medicine
    public TreatmentMedicine assignMedicine(TreatmentMedicine record) {
        validateRecord(record);

        TreatmentMedicineId id = new TreatmentMedicineId(record.getTreatmentId(), record.getMedicineId());
        if (repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: This medicine is already assigned to this treatment!");
        }

        return repository.save(record);
    }

    // view medicine under one treatment
    public List<TreatmentMedicine> getMedicinesForTreatment(String treatmentId) {
        return repository.findByTreatmentId(treatmentId);
    }

    // delete medicine
    public void removeMedicineFromTreatment(String treatmentId, String medicineId) {
        TreatmentMedicineId id = new TreatmentMedicineId(treatmentId, medicineId);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Record not found!");
        }
        repository.deleteById(id);
    }
}