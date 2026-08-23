package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.MedicalRecord;
import com.sltc.smartcare.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repository;

    // Validation Method
    private void validateMedicalRecord(MedicalRecord record) {
        // is Diagnosis empty checking here
        if (record.getDiagnosis() == null || record.getDiagnosis().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Diagnosis cannot be empty!");
        }

        // is the Date is a wrong (future data) checking here
        if (record.getTreatmentDate() != null && record.getTreatmentDate().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Treatment date cannot be in the future!");
        }
    }


    // Create new record
    public MedicalRecord createMedicalRecord(MedicalRecord record) {
        // Is the ID already exists
        if (repository.existsById(record.getTreatmentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Medical Record with ID " + record.getTreatmentId() + " already exists!");
        }

        validateMedicalRecord(record);

        return repository.save(record);
    }

    //Read All
    public List<MedicalRecord> getAllMedicalRecords() {
        return repository.findAll();
    }

    // Read by ID
    public MedicalRecord getMedicalRecordById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record not found with ID: " + id));
    }

    // Update
    public MedicalRecord updateMedicalRecord(String id, MedicalRecord updatedRecord) {
        MedicalRecord existingRecord = getMedicalRecordById(id);

        // before update check the data is correct
        validateMedicalRecord(updatedRecord);

        existingRecord.setPatientId(updatedRecord.getPatientId());
        existingRecord.setDoctorId(updatedRecord.getDoctorId());
        existingRecord.setDiagnosis(updatedRecord.getDiagnosis());
        existingRecord.setPrescription(updatedRecord.getPrescription());
        existingRecord.setTreatmentDate(updatedRecord.getTreatmentDate());
        existingRecord.setTreatmentNotes(updatedRecord.getTreatmentNotes());

        return repository.save(existingRecord);
    }

    // Delete
    public void deleteMedicalRecord(String id) {
        MedicalRecord existingRecord = getMedicalRecordById(id);
        repository.delete(existingRecord);
    }
}