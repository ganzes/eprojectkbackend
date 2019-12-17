package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.domains.MovieDto;
import com.kodilla.eprojectkbackend.exceptions.MovieNotFoundException;
import com.kodilla.eprojectkbackend.mappers.MovieMapper;
import com.kodilla.eprojectkbackend.repositories.MovieRepository;
import com.kodilla.eprojectkbackend.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @PostMapping(value = "/createMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {
        LOGGER.info("Started method createMovie in MovieController.");

        movieService.createMovie(movieMapper.mapToMovie(movieDto));

        LOGGER.info("Ended method createMovie in MovieController.");
    }

    @GetMapping(value = "/getMovie")
    public MovieDto getMovie(@RequestParam Long movieID) throws MovieNotFoundException {
        LOGGER.info("Started method getMovie in MovieController.");
        return movieMapper.mapToMovieDto(movieService.findMovieByID(movieID));
    }

    @GetMapping(value = "/getMovies")
    public List<MovieDto> getMovies() {
        LOGGER.info("Started method getMovies in MovieController.");
        LOGGER.info("Ended method getMovie in MovieController.");

        return movieMapper.mapToMovieDtoList(movieService.getAllMovie());
    }

    @PutMapping(value = "/updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) throws MovieNotFoundException {
        LOGGER.info("Started method updateMovie in MovieController.");

        Movie movie = movieRepository.findById(movieDto.getMovieID()).orElseThrow(MovieNotFoundException::new);
        movie.setMovieTitle(movieDto.getMovieTitle());
        movie.setMovieDirector(movieDto.getMovieDirector());
        movie.setMovieRating(movieDto.getMovieRating());
        Movie updateMovie = movieService.updateMovie(movie);

        LOGGER.info("Ended method deleteMovie in MovieController.");

        return movieMapper.mapToMovieDto(updateMovie);
    }

    @DeleteMapping(value = "/deleteMovie")
    public void deleteMovie(@RequestParam Long movieID) throws MovieNotFoundException {
        LOGGER.info("Started method deleteMovie in MovieController.");

        movieService.deleteMovieByID(movieID);

        LOGGER.info("Ended method deleteMovie in MovieController.");
    }

    @DeleteMapping(value = "/deleteAllMovies")
    public void deleteAllMovies() {
        LOGGER.info("Started method deleteAllMovies in MovieController.");

        movieService.deleteAllMovies();

        LOGGER.info("Ended method deleteAllMovies in MovieController.");
    }

    @GetMapping(value = "/getMovieByDirector")
    public List<MovieDto> getMovieByDirector(@RequestParam String movieDirector) {
        LOGGER.info("Started method getMovieByDirector in MovieController.");

        return movieMapper.mapToMovieDtoList(movieService.findMovieByDirector(movieDirector));
    }

    @GetMapping(value = "/getMovieByRating")
    public List<MovieDto> getMovieByRating(@RequestParam String movieRating) {
        LOGGER.info("Started method getMovieByRating in MovieController.");

        return movieMapper.mapToMovieDtoList(movieService.findMovieByRating(movieRating));
    }

    @GetMapping(value = "/countAllMovies")
    public Long countAllMovies() {
        LOGGER.info("Started method countAllMovies in MovieController.");
        long allMovies = movieService.countAllMovies();

        LOGGER.info("Ended method countAllMovies in MovieController.");
        return allMovies;
    }

}
