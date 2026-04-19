package com.moviebooking.showservice.config;

import com.moviebooking.showservice.model.*;
import com.moviebooking.showservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            MovieRepository movieRepository,
            TheatreRepository theatreRepository,
            ScreenRepository screenRepository,
            ShowRepository showRepository) {

        return args -> {

            if (movieRepository.count() > 0) {
                return;
            }

            Movie movie = new Movie();
            movie.setName("Avengers");
            movie.setLanguage("English");
            movie.setGenre("Action");
            movie.setDurationMinutes(180);
            movie = movieRepository.save(movie);

            Theatre theatre = new Theatre();
            theatre.setName("PVR Whitefield");
            theatre.setCity("Bangalore");
            theatre.setAddress("Whitefield Main Road");
            theatre.setTotalSeats(300);
            theatre.setTotalScreens(3);
            theatre = theatreRepository.save(theatre);

            Screen screen = new Screen();
            screen.setScreenName("Screen 1");
            screen.setCapacity(120);
            screen.setTheatre(theatre);
            screen = screenRepository.save(screen);

            Show show = new Show();
            show.setMovie(movie);
            show.setScreen(screen);
            show.setShowDate("2026-04-20");
            show.setStartTime("10:00");
            show.setEndTime("13:00");
            show.setPrice(250.0);
            show = showRepository.save(show);

            System.out.println("Movie ID: " + movie.getId());
            System.out.println("Theatre ID: " + theatre.getId());
            System.out.println("Screen ID: " + screen.getId());
            System.out.println("Show ID: " + show.getId());

            System.out.println("Sample data loaded.");
        };
    }
}