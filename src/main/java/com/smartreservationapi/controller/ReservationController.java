package com.smartreservationapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartreservationapi.dto.ReservationRequest;
import com.smartreservationapi.dto.ReservationResponse;
import com.smartreservationapi.entity.Reservation;
import com.smartreservationapi.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
