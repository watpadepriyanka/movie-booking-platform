package com.moviebooking.seatservice.service;

import java.util.List;
import com.moviebooking.seatservice.model.Seat;


public interface SeatService {


    List<Seat> getSeatsByShow(Long showId);

    Seat lockSeat(Long showId, String seatNumber);

    Seat confirmSeat(Long showId, String seatNumber);

    Seat releaseSeat(Long showId, String seatNumber);
}
