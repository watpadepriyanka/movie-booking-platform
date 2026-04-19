package com.moviebooking.showservice.repository;

import com.moviebooking.showservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShowRepository   extends JpaRepository<Show, Long> {

    List<Show> findByMovieIdAndShowDate(Long movieId, String showDate);
}
