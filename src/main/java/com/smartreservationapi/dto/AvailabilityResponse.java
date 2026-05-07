package com.smartreservationapi.dto;

public class AvailabilityResponse {
    private Long roomId;
    private boolean available;

    public AvailabilityResponse() {
    }

    public AvailabilityResponse(Long roomId, boolean available) {
        this.roomId = roomId;
        this.available = available;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
