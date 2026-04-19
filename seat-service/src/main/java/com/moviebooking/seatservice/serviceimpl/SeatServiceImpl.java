package com.moviebooking.seatservice.serviceimpl;

import com.moviebooking.seatservice.exception.SeatUnavailableException;
import com.moviebooking.seatservice.model.Booking;
import com.moviebooking.seatservice.model.Seat;
import com.moviebooking.seatservice.model.SeatStatus;
import com.moviebooking.seatservice.repository.BookingRepository;
import com.moviebooking.seatservice.repository.SeatRepository;
import com.moviebooking.seatservice.service.SeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    public SeatServiceImpl(SeatRepository seatRepository, BookingRepository bookingRepository) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
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

    @Transactional
    @Override
    public Booking bookSeat(Long showId, String seatNumber, String customerName) {

        Seat seat = lockSeat(showId, seatNumber);

        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        Booking booking = Booking.builder()
                .showId(showId)
                .seatNumber(seatNumber)
                .customerName(customerName)
                .amount(250.0)
                .status("CONFIRMED")
                .bookedAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }
}