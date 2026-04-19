package com.moviebooking.seatservice.repository;

import com.moviebooking.seatservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking,Long> {
}
