package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Appointment;
import com.sltc.smartcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // --- Validation Method ---
    private void validateAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() == null || appointment.getAppointmentId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Appointment ID cannot be empty!");
        }
        if (appointment.getPatientId() == null || appointment.getPatientId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Patient ID cannot be empty!");
        }
        if (appointment.getDoctorId() == null || appointment.getDoctorId().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Doctor ID cannot be empty!");
        }
        if (appointment.getAppointmentDate() == null || appointment.getAppointmentTime() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Appointment Date and Time cannot be empty!");
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getDoctorSchedule(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Appointment bookAppointment(Appointment appointment) {

        // 1. පරණ ID එකක් දැනටමත් තියෙනවද බලනවා
        if (appointmentRepository.existsById(appointment.getAppointmentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Appointment with ID " + appointment.getAppointmentId() + " already exists!");
        }

        // 2. හිස් ඩේටා තියෙනවද බලනවා
        validateAppointment(appointment);

        // 3. අතීතයේ දවසක්ද බලනවා
        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Appointment date cannot be in the past.");
        }

        // 4. Double Booking වෙනවද බලනවා
        boolean isClash = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (isClash) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Doctor already has an appointment scheduled at this exact date and time.");
        }

        appointment.setAppointmentStatus("Occupied");
        return appointmentRepository.save(appointment);
    }

    // Appointment එක ඩේටාබේස් එකෙන් මකා දැමීම (Cancel)
    public void cancelAppointment(String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Appointment not found with ID: " + appointmentId));

        appointmentRepository.delete(appointment);
    }
}