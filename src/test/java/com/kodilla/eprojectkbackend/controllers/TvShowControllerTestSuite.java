package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.eprojectkbackend.configuration.LocalDateAdapter;
import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.domains.TvShowDto;
import com.kodilla.eprojectkbackend.mappers.TvShowMapper;
import com.kodilla.eprojectkbackend.services.TvShowService;
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
@WebMvcTest(TvShowController.class)
public class TvShowControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TvShowService tvShowService;

    @MockBean
    private TvShowMapper tvShowMapper;

    @Test
    public void getEmptyTvShowsTest() throws Exception {
        //Given
        List<TvShow> tvShowListTest = new ArrayList<>();

        when(tvShowService.getAllTvShow()).thenReturn(tvShowListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/getTvShows").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void getTvShowsTest() throws Exception {
        //Given
        List<TvShow> tvShowListTest = new ArrayList<>();
        tvShowListTest.add(new TvShow(1L, "testTvShowTitle",
                "testTvShowCategory", "testTvShowRating", LocalDate.now()));

        List<TvShowDto> tvShowDtoListTest = new ArrayList<>();
        tvShowDtoListTest.add(new TvShowDto(1L, "testTvShowTitleDto",
                "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now()));

        when(tvShowService.getAllTvShow()).thenReturn(tvShowListTest);
        when(tvShowMapper.mapToTvShowDtoList(tvShowListTest)).thenReturn(tvShowDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/getTvShows").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tvShowID", is(1)))
                .andExpect(jsonPath("$[0].tvShowTitle", is("testTvShowTitleDto")))
                .andExpect(jsonPath("$[0].tvShowCategory", is("testTvShowCategoryDto")))
                .andExpect(jsonPath("$[0].tvShowRating", is("testTvShowRatingDto")));
        //.andExpect(jsonPath("$[0].tvShowCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getTvShowTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        long tvShowIDTest = tvShowTest.getTvShowID();

        TvShowDto tvShowDtoTest = new TvShowDto(1L, "testTvShowTitleDto", "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());

        when(tvShowService.findTvShowByID(tvShowIDTest)).thenReturn(tvShowTest);
        when(tvShowMapper.mapToTvShowDto(tvShowTest)).thenReturn(tvShowDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/getTvShow").contentType(MediaType.APPLICATION_JSON)
                .param("tvShowID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tvShowID", is(1)))
                .andExpect(jsonPath("$.tvShowTitle", is("testTvShowTitleDto")))
                .andExpect(jsonPath("$.tvShowCategory", is("testTvShowCategoryDto")))
                .andExpect(jsonPath("$.tvShowRating", is("testTvShowRatingDto")));
        //.andExpect(jsonPath("$.tvShowCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getTvShowByCategoryTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        String tvShowCategoryTest = tvShowTest.getTvShowCategory();

        List<TvShow> tvShowListTest = new ArrayList<>();
        tvShowListTest.add(tvShowTest);

        TvShowDto tvShowDtoTest = new TvShowDto(1L, "testTvShowTitleDto", "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());
        List<TvShowDto> tvShowDtoListTest = new ArrayList<>();
        tvShowDtoListTest.add(tvShowDtoTest);

        when(tvShowService.findTvShowByCategory(tvShowCategoryTest)).thenReturn(tvShowListTest);
        when(tvShowMapper.mapToTvShowDtoList(tvShowListTest)).thenReturn(tvShowDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/getTvShowByCategory").contentType(MediaType.APPLICATION_JSON)
                .param("tvShowCategory", "testTvShowCategory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tvShowID", is(1)))
                .andExpect(jsonPath("$[0].tvShowTitle", is("testTvShowTitleDto")))
                .andExpect(jsonPath("$[0].tvShowCategory", is("testTvShowCategoryDto")))
                .andExpect(jsonPath("$[0].tvShowRating", is("testTvShowRatingDto")));
        //.andExpect(jsonPath("$[0].tvShowCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getTvShowByRatingTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        String tvShowRatingTest = tvShowTest.getTvShowRating();

        List<TvShow> tvShowListTest = new ArrayList<>();
        tvShowListTest.add(tvShowTest);

        TvShowDto tvShowDtoTest = new TvShowDto(1L, "testTvShowTitleDto", "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());
        List<TvShowDto> tvShowDtoListTest = new ArrayList<>();
        tvShowDtoListTest.add(tvShowDtoTest);

        when(tvShowService.findTvShowByRating(tvShowRatingTest)).thenReturn(tvShowListTest);
        when(tvShowMapper.mapToTvShowDtoList(tvShowListTest)).thenReturn(tvShowDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/getTvShowByRating").contentType(MediaType.APPLICATION_JSON)
                .param("tvShowRating", "testTvShowRating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tvShowID", is(1)))
                .andExpect(jsonPath("$[0].tvShowTitle", is("testTvShowTitleDto")))
                .andExpect(jsonPath("$[0].tvShowCategory", is("testTvShowCategoryDto")))
                .andExpect(jsonPath("$[0].tvShowRating", is("testTvShowRatingDto")));
        //.andExpect(jsonPath("$[0].tvShowCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void deleteTvShowTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        long tvShowIDTest = tvShowTest.getTvShowID();

        when(tvShowService.findTvShowByID(tvShowIDTest)).thenReturn(tvShowTest);

        //When & Then
        mockMvc.perform(delete("/eprojectk/tvShow/deleteTvShow").contentType(MediaType.APPLICATION_JSON)
                .param("tvShowID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTvShowsTest() throws Exception {
        //Given
        doNothing().when(tvShowService).deleteAllTvShows();

        //When & Then
        mockMvc.perform(delete("/eprojectk/tvShow/deleteAllTvShows").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createTvShowTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory",
                "testTvShowRating", LocalDate.now());

        TvShowDto tvShowDtoTest = new TvShowDto(1L, "testTvShowTitleDto",
                "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());

        Gson gson = new GsonBuilder()
                //  .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        given(this.tvShowService.createTvShow(tvShowTest)).willReturn(tvShowTest);

        String jsonContent = gson.toJson(tvShowDtoTest);

        //When & Then
        mockMvc.perform(post("/eprojectk/tvShow/createTvShow")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void countAllTvShowsTest() throws Exception {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory",
                "testTvShowRating", LocalDate.now());
        TvShow tvShowTest2 = new TvShow(2L, "testTvShowTitle", "testTvShowCategory",
                "testTvShowRating", LocalDate.now());
        TvShowDto tvShowDtoTest = new TvShowDto(1L, "testTvShowTitleDto",
                "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());
        TvShowDto tvShowDtoTest2 = new TvShowDto(2L, "testTvShowTitleDto",
                "testTvShowCategoryDto", "testTvShowRatingDto", LocalDate.now());

        long currentTvShowsSize = 2;

        when(tvShowService.createTvShow(tvShowTest)).thenReturn(tvShowTest);
        when(tvShowService.createTvShow(tvShowTest2)).thenReturn(tvShowTest2);
        when(tvShowMapper.mapToTvShowDto(tvShowTest)).thenReturn(tvShowDtoTest);
        when(tvShowMapper.mapToTvShowDto(tvShowTest2)).thenReturn(tvShowDtoTest2);

        when(tvShowService.countAllTvShows()).thenReturn(currentTvShowsSize);

        //When & Then
        mockMvc.perform(get("/eprojectk/tvShow/countAllTvShows").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    public void updateTvShowTest() throws Exception {
        //Given
        TvShow tvShowTestUpdate = new TvShow(1L, "testTvShowTitleUpdate",
                "testTvShowCategoryUpdate", "testTvShowRatingUpdate", LocalDate.now());
        TvShowDto tvShowTestUpdateDto = new TvShowDto(1L,
                "testTvShowTitleUpdateDto", "testTvShowCategoryUpdateDto", "testTvShowRatingUpdateDto", LocalDate.now());

        when(tvShowMapper.mapToTvShow(ArgumentMatchers.any(TvShowDto.class))).thenReturn(tvShowTestUpdate);
        when(tvShowService.updateTvShow(tvShowTestUpdate)).thenReturn(tvShowTestUpdate);
        when(tvShowMapper.mapToTvShowDto(tvShowTestUpdate)).thenReturn(tvShowTestUpdateDto);

        Gson gson = new GsonBuilder()
                // .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(tvShowTestUpdateDto);

        System.out.println("wartosc jsonContent" + jsonContent);

        //When & Then
        mockMvc.perform(put("/eprojectk/tvShow/updateTvShow")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.tvShowID", is(1)))
                .andExpect(jsonPath("$.tvShowTitle", is("testTvShowTitleUpdateDto")))
                .andExpect(jsonPath("$.tvShowCategory", is("testTvShowCategoryUpdateDto")))
                .andExpect(jsonPath("$.tvShowRating", is("testTvShowRatingUpdateDto")));
    }
}
