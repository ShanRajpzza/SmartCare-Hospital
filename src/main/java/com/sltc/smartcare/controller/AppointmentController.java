package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Appointment;
import com.sltc.smartcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 1. Get all appointments
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch appointments.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Get doctor schedule by Doctor ID
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getDoctorSchedule(@PathVariable String doctorId) {
        try {
            List<Appointment> schedule = appointmentService.getDoctorSchedule(doctorId);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Could not fetch schedule for the doctor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Book new appointment
    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
            return new ResponseEntity<>(bookedAppointment, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: The specified Patient ID or Doctor ID does not exist in the database.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while booking the appointment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Cancel an appointment (Delete the record completely)
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable String id) {
        try {
            appointmentService.cancelAppointment(id);
            return new ResponseEntity<>("Appointment deleted successfully!", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("System Error: Something went wrong while deleting the appointment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}