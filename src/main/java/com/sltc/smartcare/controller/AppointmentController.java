package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Appointment;
import com.sltc.smartcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get doctor schedule by Doctor ID
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getDoctorSchedule(@PathVariable String doctorId) {
        return appointmentService.getDoctorSchedule(doctorId);
    }

    // Book new appointment
    @PostMapping
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    // Cancel an appointment
    @PutMapping("/cancel/{id}")
    public Appointment cancelAppointment(@PathVariable String id) {
        return appointmentService.cancelAppointment(id);
    }
}