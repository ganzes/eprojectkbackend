package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.domains.MovieDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public Movie mapToMovie(final MovieDto movieDto) {
        return new Movie(
                movieDto.getMovieID(),
                movieDto.getMovieTitle(),
                movieDto.getMovieDirector(),
                movieDto.getMovieRating(),
                movieDto.getMovieCreated()
        );
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getMovieID(),
                movie.getMovieTitle(),
                movie.getMovieDirector(),
                movie.getMovieRating(),
                movie.getMovieCreated()
        );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movieList) {
        return movieList.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }

    public List<Movie> mapToMovieList(final List<MovieDto> movieDtoList) {
        return movieDtoList.stream()
                .map(this::mapToMovie)
                .collect(Collectors.toList());
    }
}