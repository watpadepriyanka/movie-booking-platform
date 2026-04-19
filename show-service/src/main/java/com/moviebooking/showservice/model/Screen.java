package com.moviebooking.showservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String screenName;   // Screen 1, Screen 2

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
