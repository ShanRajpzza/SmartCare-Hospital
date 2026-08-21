package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Admission;
import com.sltc.smartcare.entity.Room;
import com.sltc.smartcare.repository.AdmissionRepository;
import com.sltc.smartcare.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Business Rule: Admit patient and allocate room if available
    @Transactional
    public Admission admitPatient(Admission admission) {
        Room room = roomRepository.findById(admission.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + admission.getRoom().getRoomId()));

        // Business Rule Validation: Room availability check
        if ("Not Available".equalsIgnoreCase(room.getAvailability())) {
            throw new IllegalStateException("Selected room is currently not available.");
        }

        // Update room status to Not Available upon admission
        room.setAvailability("Not Available");
        roomRepository.save(room);

        admission.setRoom(room);
        admission.setAdmissionStatus("Admitted");
        return admissionRepository.save(admission);
    }

    // Business Rule: Discharge patient and free up the allocated room
    @Transactional
    public Admission dischargePatient(String admissionId, LocalDate dischargeDate) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new RuntimeException("Admission record not found with ID: " + admissionId));

        admission.setDischargeDate(dischargeDate);
        admission.setAdmissionStatus("Discharged");

        // Release room back to Available status
        Room room = admission.getRoom();
        if (room != null) {
            room.setAvailability("Available");
            roomRepository.save(room);
        }

        return admissionRepository.save(admission);
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public List<Admission> getAdmissionsByPatientId(String patientId) {
        return admissionRepository.findByPatientId(patientId);
    }
}