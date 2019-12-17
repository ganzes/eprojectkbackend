package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.exceptions.MovieNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovie(){
        return movieRepository.findAll();
    }

    public Movie findMovieByID(long movieID) throws MovieNotFoundException{
        return movieRepository.findById(movieID).orElseThrow(MovieNotFoundException::new);
    }

    public Movie createMovie(final Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        Movie updateMovie = movieRepository.findById(movie.getMovieID()).orElseThrow(MovieNotFoundException::new);
        updateMovie.setMovieTitle(movie.getMovieTitle());
        updateMovie.setMovieDirector(movie.getMovieDirector());
        updateMovie.setMovieRating(movie.getMovieRating());

        return movieRepository.save(updateMovie);
    }

    public void deleteMovieByID(long movieID) throws MovieNotFoundException {
        Movie deleteMovie = movieRepository.findById(movieID).orElseThrow(MovieNotFoundException::new);
        movieRepository.delete(deleteMovie);
    }

    public void deleteAllMovies(){
        movieRepository.deleteAll();
    }

    public List<Movie> findMovieByDirector(String movieDirector) {
        return movieRepository.findByMovieDirector(movieDirector);
    }

    public List<Movie> findMovieByRating(String movieDescription) {
        return movieRepository.findByMovieRating(movieDescription);
    }

    public long countAllMovies(){
        return movieRepository.count();
    }
}