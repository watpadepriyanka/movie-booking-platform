package com.moviebooking.seatservice.serviceimpl;

import com.moviebooking.seatservice.exception.SeatUnavailableException;
import com.moviebooking.seatservice.model.Seat;
import com.moviebooking.seatservice.model.SeatStatus;
import com.moviebooking.seatservice.repository.SeatRepository;
import com.moviebooking.seatservice.service.SeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getSeatsByShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    @Override
    @Transactional
    public Seat lockSeat(Long showId, String seatNumber) {
        Seat seat = seatRepository
                .findByShowIdAndSeatNumber(showId, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        // If already locked, check expiry
        if (seat.getStatus() == SeatStatus.LOCKED &&
                seat.getLockedAt() != null &&
                seat.getLockedAt().plusMinutes(5).isBefore(LocalDateTime.now())) {

            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setLockedAt(null);
        }

        // If still unavailable
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new SeatUnavailableException("Seat already booked");
        }

        // Lock now
        seat.setStatus(SeatStatus.LOCKED);
        seat.setLockedAt(LocalDateTime.now());

        return seatRepository.save(seat);
    }

    @Override
    @Transactional
    public Seat confirmSeat(Long showId, String seatNumber) {

        Seat seat = seatRepository
                .findByShowIdAndSeatNumber(showId, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setStatus(SeatStatus.BOOKED);
        seat.setLockedAt(null);
        return seatRepository.save(seat);
    }

    @Override
    @Transactional
    public Seat releaseSeat(Long showId, String seatNumber) {

        Seat seat = seatRepository
                .findByShowIdAndSeatNumber(showId, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setStatus(SeatStatus.AVAILABLE);
        seat.setLockedAt(null);
        return seatRepository.save(seat);
    }
}