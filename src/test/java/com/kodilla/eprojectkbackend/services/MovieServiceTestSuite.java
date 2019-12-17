package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.exceptions.MovieNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MovieServiceTestSuite {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    public void createMovieTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());

        //When
        when(movieRepository.save(movieTest)).thenReturn((movieTest));
        movieService.createMovie(movieTest);

        //Then
        verify(movieRepository, times(1)).save(movieTest);
    }

    @Test
    public void getAllMovieTest() {
        //Given
        List<Movie> movieListTest = new ArrayList<>();
        movieListTest.add(new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now()));
        movieListTest.add(new Movie(2L, "testMovieTitle2", "testMovieDirector2", "testMovieRating2", LocalDate.now()));

        //When
        when(movieService.getAllMovie()).thenReturn(movieListTest);

        //Then
        assertEquals(2, movieListTest.size());
    }

    @Test
    public void findMovieByDirectorTest() {
        //Given
        List<Movie> movieListTest = new ArrayList<>();
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        movieListTest.add(movieTest);

        String movieTestDirector = movieTest.getMovieDirector();
        String movieTestText = movieTest.getMovieTitle();

        //When
        when(movieRepository.findByMovieDirector(movieTestDirector)).thenReturn(movieListTest);

        //Then
        assertEquals("testMovieTitle", movieTestText);
        assertEquals("testMovieDirector", movieTestDirector);
    }

    @Test
    public void findMovieByIDTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        Long movieTestID = movieTest.getMovieID();
        Optional<Movie> optionalMovieTest = Optional.of(movieTest);
        String movieTestText = movieTest.getMovieTitle();
        //When
        when(movieRepository.findById(movieTestID)).thenReturn(optionalMovieTest);

        //Then
        assertEquals("testMovieTitle", movieTestText);
    }

    @Test
    public void findMovieByRating() {
        //Given
        List<Movie> movieListTest = new ArrayList<>();
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        movieListTest.add(movieTest);

        String movieTestRating = movieTest.getMovieRating();
        String movieTestText = movieTest.getMovieTitle();

        //When
        when(movieRepository.findByMovieDirector(movieTestRating)).thenReturn(movieListTest);

        //Then
        assertEquals("testMovieTitle", movieTestText);
        assertEquals("testMovieRating", movieTestRating);
    }

    @Test
    public void deleteMovieByIDTest() throws MovieNotFoundException {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());

        Long movieTestID = movieTest.getMovieID();

        //When
        when(movieRepository.findById(movieTestID)).thenReturn(Optional.of(movieTest));

        doNothing().when(movieRepository).delete(movieTest);
        movieService.deleteMovieByID(movieTestID);

        //Then
        verify(movieRepository, times(1)).delete(movieTest);
    }

    @Test
    public void deleteAllMovies() throws MovieNotFoundException {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());

        //When
        doNothing().when(movieRepository).deleteAll();
        movieService.deleteAllMovies();

        //Then
        verify(movieRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllMoviesTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        movieRepository.save(movieTest);
        long countAllMovies = movieRepository.count();

        //When
        when(movieService.countAllMovies()).thenReturn(countAllMovies);

        //Then
        verify(movieRepository, times(1)).count();
    }
}
