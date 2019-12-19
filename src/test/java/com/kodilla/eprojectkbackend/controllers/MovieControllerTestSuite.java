package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.eprojectkbackend.configuration.LocalDateAdapter;
import com.kodilla.eprojectkbackend.domains.BookDto;
import com.kodilla.eprojectkbackend.domains.Movie;
import com.kodilla.eprojectkbackend.domains.MovieDto;
import com.kodilla.eprojectkbackend.mappers.MovieMapper;
import com.kodilla.eprojectkbackend.repositories.MovieRepository;
import com.kodilla.eprojectkbackend.services.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieMapper movieMapper;

    @Test
    public void getEmptyMoviesTest() throws Exception {
        //Given
        List<Movie> movieListTest = new ArrayList<>();

        when(movieService.getAllMovie()).thenReturn(movieListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/getMovies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void getMoviesTest() throws Exception {
        //Given
        List<Movie> movieListTest = new ArrayList<>();
        movieListTest.add(new Movie(1L, "testMovieTitle",
                "testMovieDirector", "testMovieRating", LocalDate.now()));

        List<MovieDto> movieDtoListTest = new ArrayList<>();
        movieDtoListTest.add(new MovieDto(1L, "testMovieTitleDto",
                "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now()));

        when(movieService.getAllMovie()).thenReturn(movieListTest);
        when(movieMapper.mapToMovieDtoList(movieListTest)).thenReturn(movieDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/getMovies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].movieID", is(1)))
                .andExpect(jsonPath("$[0].movieTitle", is("testMovieTitleDto")))
                .andExpect(jsonPath("$[0].movieDirector", is("testMovieDirectorDto")))
                .andExpect(jsonPath("$[0].movieRating", is("testMovieRatingDto")));
        //.andExpect(jsonPath("$[0].movieCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMovieTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        long movieIDTest = movieTest.getMovieID();

        MovieDto movieDtoTest = new MovieDto(1L, "testMovieTitleDto", "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());

        when(movieService.findMovieByID(movieIDTest)).thenReturn(movieTest);
        when(movieMapper.mapToMovieDto(movieTest)).thenReturn(movieDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/getMovie").contentType(MediaType.APPLICATION_JSON)
                .param("movieID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieID", is(1)))
                .andExpect(jsonPath("$.movieTitle", is("testMovieTitleDto")))
                .andExpect(jsonPath("$.movieDirector", is("testMovieDirectorDto")))
                .andExpect(jsonPath("$.movieRating", is("testMovieRatingDto")));
        //.andExpect(jsonPath("$.movieCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMovieByDirectorTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        String movieDirectorTest = movieTest.getMovieDirector();

        List<Movie> movieListTest = new ArrayList<>();
        movieListTest.add(movieTest);

        MovieDto movieDtoTest = new MovieDto(1L, "testMovieTitleDto", "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());
        List<MovieDto> movieDtoListTest = new ArrayList<>();
        movieDtoListTest.add(movieDtoTest);

        when(movieService.findMovieByDirector(movieDirectorTest)).thenReturn(movieListTest);
        when(movieMapper.mapToMovieDtoList(movieListTest)).thenReturn(movieDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/getMovieByDirector").contentType(MediaType.APPLICATION_JSON)
                .param("movieDirector", "testMovieDirector"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].movieID", is(1)))
                .andExpect(jsonPath("$[0].movieTitle", is("testMovieTitleDto")))
                .andExpect(jsonPath("$[0].movieDirector", is("testMovieDirectorDto")))
                .andExpect(jsonPath("$[0].movieRating", is("testMovieRatingDto")));
        //.andExpect(jsonPath("$[0].movieCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMovieByRatingTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        String movieRatingTest = movieTest.getMovieRating();

        List<Movie> movieListTest = new ArrayList<>();
        movieListTest.add(movieTest);

        MovieDto movieDtoTest = new MovieDto(1L, "testMovieTitleDto", "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());
        List<MovieDto> movieDtoListTest = new ArrayList<>();
        movieDtoListTest.add(movieDtoTest);

        when(movieService.findMovieByRating(movieRatingTest)).thenReturn(movieListTest);
        when(movieMapper.mapToMovieDtoList(movieListTest)).thenReturn(movieDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/getMovieByRating").contentType(MediaType.APPLICATION_JSON)
                .param("movieRating", "testMovieRating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].movieID", is(1)))
                .andExpect(jsonPath("$[0].movieTitle", is("testMovieTitleDto")))
                .andExpect(jsonPath("$[0].movieDirector", is("testMovieDirectorDto")))
                .andExpect(jsonPath("$[0].movieRating", is("testMovieRatingDto")));
        //.andExpect(jsonPath("$[0].movieCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void deleteMovieTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector", "testMovieRating", LocalDate.now());
        long movieIDTest = movieTest.getMovieID();

        when(movieService.findMovieByID(movieIDTest)).thenReturn(movieTest);

        //When & Then
        mockMvc.perform(delete("/eprojectk/movie/deleteMovie").contentType(MediaType.APPLICATION_JSON)
                .param("movieID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllMoviesTest() throws Exception {
        //Given
        doNothing().when(movieService).deleteAllMovies();

        //When & Then
        mockMvc.perform(delete("/eprojectk/movie/deleteAllMovies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createMovieTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector",
                "testMovieRating", LocalDate.now());

        MovieDto movieDtoTest = new MovieDto(1L, "testMovieTitleDto",
                "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());

        Gson gson = new GsonBuilder()
                //  .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        given(this.movieService.createMovie(movieTest)).willReturn(movieTest);

        String jsonContent = gson.toJson(movieDtoTest);

        //When & Then
        mockMvc.perform(post("/eprojectk/movie/createMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void countAllMoviesTest() throws Exception {
        //Given
        Movie movieTest = new Movie(1L, "testMovieTitle", "testMovieDirector",
                "testMovieRating", LocalDate.now());
        Movie movieTest2 = new Movie(2L, "testMovieTitle", "testMovieDirector",
                "testMovieRating", LocalDate.now());
        MovieDto movieDtoTest = new MovieDto(1L, "testMovieTitleDto",
                "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());
        MovieDto movieDtoTest2 = new MovieDto(2L, "testMovieTitleDto",
                "testMovieDirectorDto", "testMovieRatingDto", LocalDate.now());

        long currentMoviesSize = 2;

        when(movieService.createMovie(movieTest)).thenReturn(movieTest);
        when(movieService.createMovie(movieTest2)).thenReturn(movieTest2);
        when(movieMapper.mapToMovieDto(movieTest)).thenReturn(movieDtoTest);
        when(movieMapper.mapToMovieDto(movieTest2)).thenReturn(movieDtoTest2);

        when(movieService.countAllMovies()).thenReturn(currentMoviesSize);

        //When & Then
        mockMvc.perform(get("/eprojectk/movie/countAllMovies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    public void updateMovieTest() throws Exception {
        //Given
        Movie movieTestUpdate = new Movie(1L, "testMovieTitleUpdate",
                "testMovieDirectorUpdate", "testMovieRatingUpdate", LocalDate.now());
        MovieDto movieTestUpdateDto = new MovieDto(1L,
                "testMovieTitleUpdateDto", "testMovieDirectorUpdateDto", "testMovieRatingUpdateDto", LocalDate.now());

        when(movieMapper.mapToMovie(ArgumentMatchers.any(MovieDto.class))).thenReturn(movieTestUpdate);
        when(movieService.updateMovie(movieTestUpdate)).thenReturn(movieTestUpdate);
        when(movieMapper.mapToMovieDto(movieTestUpdate)).thenReturn(movieTestUpdateDto);

        Gson gson = new GsonBuilder()
                // .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(movieTestUpdateDto);

        System.out.println("wartosc jsonContent" + jsonContent);

        //When & Then
        mockMvc.perform(put("/eprojectk/movie/updateMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.movieID", is(1)))
                .andExpect(jsonPath("$.movieTitle", is("testMovieTitleUpdateDto")))
                .andExpect(jsonPath("$.movieDirector", is("testMovieDirectorUpdateDto")))
                .andExpect(jsonPath("$.movieRating", is("testMovieRatingUpdateDto")));
    }
}
