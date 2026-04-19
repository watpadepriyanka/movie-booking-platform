package com.moviebooking.showservice.service.impl;

import com.moviebooking.showservice.model.Movie;
import com.moviebooking.showservice.model.Screen;
import com.moviebooking.showservice.model.Show;
import com.moviebooking.showservice.repository.MovieRepository;
import com.moviebooking.showservice.repository.ScreenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moviebooking.showservice.repository.ShowRepository;
import com.moviebooking.showservice.service.ShowService;

import java.util.List;

@Service
public class ShowServiceImpl  implements ShowService {

    private final ShowRepository showRepository;
    private  final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowServiceImpl(ShowRepository showRepository, MovieRepository movieRepository, ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }


    @Override
    public List<Show> getAllShows() {
        return  showRepository.findAll();
    }

    @Override
    public List<Show> getShows(Long movieId, String date) {
        return showRepository.findByMovieIdAndShowDate(movieId,date);
    }

    @Transactional
    @Override
    public Show saveShow(Show show) {

        Movie movie = movieRepository.findById(show.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Screen screen = screenRepository.findById(show.getScreen().getId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        show.setMovie(movie);
        show.setScreen(screen);

        Show saved = showRepository.save(show);

        return showRepository.findById(saved.getId()).orElse(saved);

    }
}
