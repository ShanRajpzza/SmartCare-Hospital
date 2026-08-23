package com.sltc.smartcare.controller;

import com.sltc.smartcare.entity.Room;
import com.sltc.smartcare.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // API to add or update room details
    @PostMapping
    public ResponseEntity<?> createOrUpdateRoom(@Valid @RequestBody Room room, BindingResult bindingResult) {
        // 1. Catch Validation Errors (@PositiveOrZero, @NotNull, etc.)
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // 2. Catch Service & Business Logic Exceptions
        try {
            Room savedRoom = roomService.saveRoom(room);
            return ResponseEntity.ok(savedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    // API to fetch all rooms
    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving rooms: " + e.getMessage());
        }
    }

    // API to fetch only available rooms
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableRooms() {
        try {
            List<Room> availableRooms = roomService.getAvailableRooms();
            return ResponseEntity.ok(availableRooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving available rooms: " + e.getMessage());
        }
    }

    // API to fetch room details by Room ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id") String roomId) {
        try {
            return roomService.getRoomById(roomId)
                    .map(room -> ResponseEntity.ok((Object) room))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Room not found with ID: " + roomId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching room details: " + e.getMessage());
        }
    }
}