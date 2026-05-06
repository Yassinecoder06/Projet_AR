package com.smartreservationapi.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartreservationapi.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select count(r) > 0 from Reservation r "
            + "where r.room.id = :roomId and r.date = :date "
            + "and (r.startTime < :endTime and r.endTime > :startTime)")
    boolean existsOverlappingReservation(@Param("roomId") Long roomId,
                                         @Param("date") LocalDate date,
                                         @Param("startTime") LocalTime startTime,
                                         @Param("endTime") LocalTime endTime);
}
