package com.movie.recommendation.controller;

import com.movie.recommendation.model.MovieTitle;
import com.movie.recommendation.repo.MovieTitleRepository;
import com.movie.recommendation.service.MovieTitleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class MovieTitleController {

    @Autowired MovieTitleRepository movieTitleRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieTitleController.class);

    private final MovieTitleService movieTitleService;

    @Autowired
    public MovieTitleController(MovieTitleService movieTitleService) {
        this.movieTitleService = movieTitleService;
    }


    @PostMapping(path="/loadtitle", produces = "application/json")
    public ResponseEntity<HttpStatus> loadMovieTitles() throws IOException {
        logger.info("-------> Loading movie titles...");
        List<MovieTitle> titles = movieTitleService.getTitles();
        movieTitleRepository.saveAll(titles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/settitle", produces = "application/json")
    public ResponseEntity<HttpStatus> saveTitle(MovieTitle movieTitle) {
        logger.info("-------> saving movie title...");
        movieTitleRepository.save(movieTitle);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/getTitle", produces = "application/json")
    public @ResponseBody Iterable<MovieTitle> getTitle() {
        logger.info("-------> Retrieving all movie titles...");
        return movieTitleRepository.findAll();
    }

}
