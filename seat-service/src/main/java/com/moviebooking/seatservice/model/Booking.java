package com.moviebooking.seatservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showId;

    private String seatNumber;

    private String customerName;

    private Double amount;

    private String status; // CONFIRMED

    private LocalDateTime bookedAt;
}