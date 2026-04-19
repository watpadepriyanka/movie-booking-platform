package com.moviebooking.seatservice.repository;

import com.moviebooking.seatservice.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowId(Long showId);

    Optional<Seat> findByShowIdAndSeatNumber(Long showId, String seatNumber);
}