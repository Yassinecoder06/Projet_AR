package com.smartreservationapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartreservationapi.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
