package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.domains.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieMapperTestSuite {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void testMapToMovieTest() {
        //Given
        MovieDto movieDto = new MovieDto(1L, "TestMovieTitle", "TestMovieDirector", "TestMovieRating", LocalDate.now());

        //When
        Movie movie = movieMapper.mapToMovie(movieDto);

        //Then
        assertEquals("TestMovieTitle", movie.getMovieTitle());
    }

    @Test
    public void testMapToMovieDtoTest() {
        //Given
        Movie movie = new Movie(1L,"TestMovieTitle", "TestMovieDirector", "TestMovieRating", LocalDate.now());

        //When
        MovieDto movieDto = movieMapper.mapToMovieDto(movie);

        //Then
        assertEquals("TestMovieTitle", movieDto.getMovieTitle());
    }

    @Test
    public void testMapToMovieDtoListTest() {
        //Given
        Movie movie = new Movie(1L,"TestMovieTitle", "TestMovieDirector", "TestMovieRating", LocalDate.now());
        Movie movie2 = new Movie(1L,"TestMovieTitle2", "TestMovieDirector2", "TestMovieRating2", LocalDate.now());

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie2);

        //When
        List<MovieDto> movieDtoList = movieMapper.mapToMovieDtoList(movieList);

        //Then
        assertEquals(2, movieDtoList.size());
    }

    @Test
    public void testMapToMovieListTest() {
        //Given
        MovieDto movieDto = new MovieDto(1L, "TestMovieTitle", "TestMovieDirector", "TestMovieRating", LocalDate.now());
        MovieDto movieDto2 = new MovieDto(2L, "TestMovieTitle2", "TestMovieDirector2", "TestMovieRating2", LocalDate.now());

        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDto);
        movieDtoList.add(movieDto2);

        //When
        List<Movie> movieList = movieMapper.mapToMovieList(movieDtoList);

        //Then
        assertEquals(2, movieList.size());
    }
}