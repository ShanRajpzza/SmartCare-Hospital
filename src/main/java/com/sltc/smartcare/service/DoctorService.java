package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Department;
import com.sltc.smartcare.entity.Doctor;
import com.sltc.smartcare.repository.DepartmentRepository;
import com.sltc.smartcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Validates mandatory fields before saving or updating
    private void validateDoctor(Doctor doctor) {
        if (doctor.getDoctorId() == null || doctor.getDoctorId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Doctor ID cannot be empty!");
        }
        if (doctor.getDoctorName() == null || doctor.getDoctorName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Doctor Name cannot be empty!");
        }
        if (doctor.getSpecialization() == null || doctor.getSpecialization().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Specialization cannot be empty!");
        }
        if (doctor.getConsultationFee() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Consultation Fee cannot be empty!");
        }
    }

    // Adds a new doctor with duplicate ID validation
    public Doctor addDoctor(Doctor doctor) {
        if (doctorRepository.existsById(doctor.getDoctorId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Doctor with ID " + doctor.getDoctorId() + " already exists!");
        }
        validateDoctor(doctor);
        return doctorRepository.save(doctor);
    }

    // Retrieves all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Finds a specific doctor by ID, throws 404 if not found
    public Doctor getDoctorById(String doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Doctor not found with ID: " + doctorId));
    }

    // Updates existing doctor details dynamically
    public Doctor updateDoctor(String doctorId, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(doctorId);

        if (doctorDetails.getDoctorName() != null) doctor.setDoctorName(doctorDetails.getDoctorName());
        if (doctorDetails.getContactNumber() != null) doctor.setContactNumber(doctorDetails.getContactNumber());
        if (doctorDetails.getQualification() != null) doctor.setQualification(doctorDetails.getQualification());
        if (doctorDetails.getSpecialization() != null) doctor.setSpecialization(doctorDetails.getSpecialization());
        if (doctorDetails.getConsultationFee() != null) doctor.setConsultationFee(doctorDetails.getConsultationFee());

        return doctorRepository.save(doctor);
    }

    // Deletes a doctor by ID
    public void deleteDoctor(String doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        doctorRepository.delete(doctor);
    }

    // Searches for doctors using a partial name match
    public List<Doctor> searchDoctorsByName(String name) {
        return doctorRepository.findByDoctorNameContainingIgnoreCase(name);
    }

    // Assigns an existing department to a doctor
    public Doctor assignDepartment(String doctorId, String departmentId) {
        Doctor doctor = getDoctorById(doctorId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Department not found with ID: " + departmentId));

        doctor.setDepartment(department);
        return doctorRepository.save(doctor);
    }
}