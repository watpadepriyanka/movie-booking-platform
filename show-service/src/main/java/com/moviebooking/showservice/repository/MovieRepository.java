package com.moviebooking.showservice.repository;

import com.moviebooking.showservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository  extends JpaRepository<Movie,Long> {


}
