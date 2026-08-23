package com.sltc.smartcare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "Room_ID", length = 20)
    private String roomId;

    @NotNull(message = "Category cannot be null")
    @Column(name = "Category", length = 20, nullable = false)
    private String category;

    @Column(name = "Availability")
    private String availability = "Available";

    @NotNull(message = "Room charge is required")
    @PositiveOrZero(message = "Room charge cannot be negative")
    @Column(name = "Room_charge", nullable = false)
    private BigDecimal roomCharge;

    public Room() {
    }

    public Room(String roomId, String category, String availability, BigDecimal roomCharge) {
        this.roomId = roomId;
        this.category = category;
        this.availability = availability;
        this.roomCharge = roomCharge;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public BigDecimal getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(BigDecimal roomCharge) {
        this.roomCharge = roomCharge;
    }
}