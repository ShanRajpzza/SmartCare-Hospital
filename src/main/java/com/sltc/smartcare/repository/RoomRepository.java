package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    //("Available", "Not Available")
    List<Room> findByAvailability(String availability);
}