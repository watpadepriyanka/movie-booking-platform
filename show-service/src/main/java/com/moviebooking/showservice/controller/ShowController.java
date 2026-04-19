package com.moviebooking.showservice.controller;

import com.moviebooking.showservice.model.Show;
import org.springframework.web.bind.annotation.*;
import com.moviebooking.showservice.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // Get all shows
    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    // Assignment Read Scenario
    // Browse theatres currently running selected movie by date

    @GetMapping("/search")
    public List<Show> searchShows(
            @RequestParam("movieId") Long movieId,
            @RequestParam("date") String date) {

        return showService.getShows(movieId, date);
    }

    // Create Show
    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.saveShow(show);
    }

    // Health Check
    @GetMapping("/test")
    public String test() {
        return "Show Service Running";
    }
}
