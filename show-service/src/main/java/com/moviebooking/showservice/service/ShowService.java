package com.moviebooking.showservice.service;

import java.util.List;
import com.moviebooking.showservice.model.Show;

public interface ShowService {

    List<Show> getAllShows();

    List<Show> getShows(Long movieId, String date);

    Show saveShow(Show show);
}
