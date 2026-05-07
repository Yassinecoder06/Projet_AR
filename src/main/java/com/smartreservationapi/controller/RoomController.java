package com.smartreservationapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartreservationapi.dto.AvailabilityResponse;
import com.smartreservationapi.entity.Room;
import com.smartreservationapi.service.ReservationService;
import com.smartreservationapi.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final ReservationService reservationService;

    public RoomController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}/available")
    public AvailabilityResponse checkAvailability(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
            @RequestParam @DateTimeFormat(iso = ISO.TIME) LocalTime start,
            @RequestParam @DateTimeFormat(iso = ISO.TIME) LocalTime end) {
        boolean available = reservationService.isRoomAvailable(id, date, start, end);
        return new AvailabilityResponse(id, available);
    }
}
