package com.moviebooking.seatservice.controller;

import com.moviebooking.seatservice.model.Seat;
import com.moviebooking.seatservice.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }
    @GetMapping("/{showId}")
    public List<Seat> getSeats(@PathVariable("showId") Long showId) {
        return seatService.getSeatsByShow(showId);
    }

    // Lock seat temporarily
    @PostMapping("/lock")
    public Seat lockSeat(
            @RequestParam("showId") Long showId,
            @RequestParam("seatNumber") String seatNumber) {

        return seatService.lockSeat(showId, seatNumber);
    }

    // Confirm booking
    @PostMapping("/confirm")
    public Seat confirmSeat(
            @RequestParam("showId") Long showId,
            @RequestParam("seatNumber") String seatNumber) {

        return seatService.confirmSeat(showId, seatNumber);
    }

    // Release locked seat
    @PostMapping("/release")
    public Seat releaseSeat(
            @RequestParam("showId") Long showId,
            @RequestParam("seatNumber") String seatNumber) {

        return seatService.releaseSeat(showId, seatNumber);
    }

    // Health check
    @GetMapping("/test")
    public String test() {
        return "Seat Service Running";
    }


}
