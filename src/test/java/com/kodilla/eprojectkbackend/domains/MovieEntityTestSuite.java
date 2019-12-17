package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.MovieNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovieEntityTestSuite {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void movieSaveTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        Movie savedmovieTest = movieRepository.save(movieTest);
        long movieTestID = savedmovieTest.getMovieID();

        //Then
        Assert.assertNotEquals(0, movieTestID);
        Assert.assertEquals(1L, movieTestID);

        //CleanUp
        movieRepository.deleteById(movieTestID);
        movieRepository.deleteAll();
    }

    @Test
    public void movieReadTest() throws MovieNotFoundException {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        Movie savedMovie = movieRepository.save(movieTest);
        long movieTestID = savedMovie.getMovieID();

        //Then
        Movie resultReadTest = movieRepository.findById(movieTestID).orElseThrow(MovieNotFoundException::new);

        Assert.assertEquals("testMovieTitle", resultReadTest.getMovieTitle());
        Assert.assertEquals("testMovieDirector", resultReadTest.getMovieDirector());

        //CleanUp
        movieRepository.deleteById(movieTestID);
        movieRepository.deleteAll();
    }

    @Test
    public void movieUpdateTest() throws MovieNotFoundException {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        movieRepository.save(movieTest);
        long movieTestID = movieTest.getMovieID();

        //Then
        Movie updatedMovieTest = movieRepository.findById(movieTestID).orElseThrow(MovieNotFoundException::new);
        updatedMovieTest.setMovieTitle("testMovieTitleUpdated");
        movieRepository.save(updatedMovieTest);

        Movie resultUpdateTest = movieRepository.findById(movieTestID).orElseThrow(MovieNotFoundException::new);

        //Then
        Assert.assertEquals("testMovieTitleUpdated", resultUpdateTest.getMovieTitle());

        //CleanUp
        movieRepository.deleteById(movieTestID);
        movieRepository.deleteAll();
    }

    @Test
    public void movieDeleteTest() throws MovieNotFoundException {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        Movie savedMovie = movieRepository.save(movieTest);
        long movieTestID = savedMovie.getMovieID();
        long countBeforeDelete = movieRepository.count();

        //Then
        movieRepository.deleteById(movieTestID);
        long countAfterDelete = movieRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        movieRepository.deleteAll();
    }

    @Test
    public void deleteAllMoviesTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());
        Movie movieTest2 = new Movie(2L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        movieRepository.save(movieTest);
        movieRepository.save(movieTest2);

        long countBeforeDelete = movieRepository.count();

        //Then
        movieRepository.deleteAll();
        long countAfterDelete = movieRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        movieRepository.deleteAll();
    }

    @Test
    public void countAllMoviesTest() {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());
        Movie movieTest2 = new Movie(2L, "testMovieTitle", "testMovieDirector", "9", LocalDate.now());

        //When
        movieRepository.save(movieTest);
        long countMovieCountTest = movieRepository.count();

        movieRepository.save(movieTest2);
        long countMovieCountTest2 = movieRepository.count();

        //Then
        Assert.assertEquals(1, countMovieCountTest);
        Assert.assertEquals(2, countMovieCountTest2);

        //Clean up
        movieRepository.deleteAll();
    }
}