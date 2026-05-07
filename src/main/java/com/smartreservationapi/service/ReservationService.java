package com.smartreservationapi.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartreservationapi.dto.ReservationRequest;
import com.smartreservationapi.dto.ReservationResponse;
import com.smartreservationapi.entity.Reservation;
import com.smartreservationapi.entity.Room;
import com.smartreservationapi.entity.User;
import com.smartreservationapi.exception.ReservationConflictException;
import com.smartreservationapi.exception.ResourceNotFoundException;
import com.smartreservationapi.repository.ReservationRepository;
import com.smartreservationapi.repository.RoomRepository;
import com.smartreservationapi.repository.UserRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        validateTimeRange(request.getStartTime(), request.getEndTime());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + request.getRoomId()));

        boolean hasConflict = reservationRepository.existsOverlappingReservation(
                room.getId(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime());

        if (hasConflict) {
            throw new ReservationConflictException("Reservation conflicts with an existing booking.");
        }

        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());
        reservation.setUser(user);
        reservation.setRoom(room);

        Reservation saved = reservationRepository.save(reservation);
        return toResponse(saved);
    }

    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation not found: " + id);
        }
        reservationRepository.deleteById(id);
    }

    public boolean isRoomAvailable(Long roomId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        validateTimeRange(startTime, endTime);

        if (!roomRepository.existsById(roomId)) {
            throw new ResourceNotFoundException("Room not found: " + roomId);
        }

        return !reservationRepository.existsOverlappingReservation(roomId, date, startTime, endTime);
    }

    private void validateTimeRange(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
    }

    private ReservationResponse toResponse(Reservation reservation) {
        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setDate(reservation.getDate());
        response.setStartTime(reservation.getStartTime());
        response.setEndTime(reservation.getEndTime());
        response.setUserId(reservation.getUser().getId());
        response.setUserName(reservation.getUser().getName());
        response.setRoomId(reservation.getRoom().getId());
        response.setRoomName(reservation.getRoom().getName());
        return response;
    }
}
