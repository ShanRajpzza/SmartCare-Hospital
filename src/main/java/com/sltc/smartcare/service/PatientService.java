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

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = getPatientById(id);

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setContactNumber(updatedPatient.getContactNumber());
        existingPatient.setBloodGroup(updatedPatient.getBloodGroup());
        existingPatient.setEmergencyContact(updatedPatient.getEmergencyContact());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);

    }

    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }
}