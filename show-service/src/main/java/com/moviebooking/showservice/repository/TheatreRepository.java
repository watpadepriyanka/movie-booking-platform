package com.moviebooking.showservice.repository;

import com.moviebooking.showservice.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository  extends JpaRepository<Theatre,Long> {

    List<Theatre> findByCity(String city);
}
