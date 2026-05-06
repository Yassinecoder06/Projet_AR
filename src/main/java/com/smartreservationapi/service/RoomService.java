package com.smartreservationapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartreservationapi.entity.Room;
import com.smartreservationapi.exception.ResourceNotFoundException;
import com.smartreservationapi.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
    }
}
