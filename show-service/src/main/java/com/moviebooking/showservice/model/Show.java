package com.moviebooking.showservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String showDate;
    private String startTime;
    private String endTime;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
