package com.moviebooking.seatservice.config;

import com.moviebooking.seatservice.model.Seat;
import com.moviebooking.seatservice.model.SeatStatus;
import com.moviebooking.seatservice.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {


    @Bean
    CommandLineRunner loadSeats(SeatRepository seatRepository) {

        return args -> {

            if (seatRepository.count() > 0) {
                return;
            }

            for (int i = 1; i <= 10; i++) {

                Seat seat = Seat.builder()
                        .showId(1L)
                        .seatNumber("A" + i)
                        .status(SeatStatus.AVAILABLE)
                        .build();

                seatRepository.save(seat);
            }

            for (int i = 1; i <= 10; i++) {

                Seat seat = Seat.builder()
                        .showId(1L)
                        .seatNumber("B" + i)
                        .status(SeatStatus.AVAILABLE)
                        .build();

                seatRepository.save(seat);
            }

            System.out.println("Seats Loaded.");
        };
    }
}