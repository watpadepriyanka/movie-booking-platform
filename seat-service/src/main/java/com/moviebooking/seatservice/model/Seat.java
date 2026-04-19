package com.moviebooking.seatservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showId;

    private String seatNumber;

    private LocalDateTime lockedAt;

    private SeatStatus status; // AVAILABLE, LOCKED, BOOKED

    @Version
    private Integer version;
}