package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    // Double bookings නවත්තන්න පාවිච්චි කරන method එක
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(String doctorId, LocalDate appointmentDate, LocalTime appointmentTime);

    // දොස්තරගේ ෂෙඩියුල් එක ගන්න method එක
    List<Appointment> findByDoctorId(String doctorId);
}