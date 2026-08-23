package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Patient;
import com.sltc.smartcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Advanced Validation
    private void validatePatient(Patient patient) throws Exception {
        if (patient.getPatientId() == null || patient.getPatientId().trim().isEmpty()) {
            throw new Exception("Error: Patient ID cannot be empty!");
        }
        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            throw new Exception("Error: Patient Name cannot be empty!");
        }
        if (patient.getContactNumber() == null || patient.getContactNumber().trim().isEmpty()) {
            throw new Exception("Error: Contact Number cannot be empty!");
        }
    }

    public Patient registerPatient(Patient patient) throws Exception {
        if (patientRepository.existsById(patient.getPatientId())) {
            throw new Exception("Error: Patient with ID " + patient.getPatientId() + " already exists!");
        }
        validatePatient(patient);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) throws Exception {
        return patientRepository.findById(id)
                .orElseThrow(() -> new Exception("Error: Patient not found with ID: " + id));
    }

    public Patient updatePatient(String id, Patient updatedPatient) throws Exception {
        Patient existingPatient = getPatientById(id);

        // Validation එක්ක Update
        if (updatedPatient.getName() != null && !updatedPatient.getName().trim().isEmpty()) {
            existingPatient.setName(updatedPatient.getName());
        }
        if (updatedPatient.getDob() != null) existingPatient.setDob(updatedPatient.getDob());
        if (updatedPatient.getGender() != null) existingPatient.setGender(updatedPatient.getGender());
        if (updatedPatient.getAddress() != null) existingPatient.setAddress(updatedPatient.getAddress());
        if (updatedPatient.getContactNumber() != null) existingPatient.setContactNumber(updatedPatient.getContactNumber());

        if (updatedPatient.getBloodGroup() != null) existingPatient.setBloodGroup(updatedPatient.getBloodGroup());
        if (updatedPatient.getEmergencyContact() != null) existingPatient.setEmergencyContact(updatedPatient.getEmergencyContact());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(String id) throws Exception {
        if (!patientRepository.existsById(id)) {
            throw new Exception("Error: Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }
}