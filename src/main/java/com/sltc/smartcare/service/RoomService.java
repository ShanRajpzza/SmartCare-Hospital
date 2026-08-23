package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Room;
import com.sltc.smartcare.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Save or update room with validation
    public Room saveRoom(Room room) {
        if (room.getRoomCharge() != null && room.getRoomCharge().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Room charge cannot be negative.");
        }
        return roomRepository.save(room);
    }

    // Retrieve all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Find room by ID
    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    // Get list of available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailability("Available");
    }
}