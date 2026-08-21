package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(String doctorId, LocalDate appointmentDate, LocalTime appointmentTime);

    List<Appointment> findByDoctorId(String doctorId);
}