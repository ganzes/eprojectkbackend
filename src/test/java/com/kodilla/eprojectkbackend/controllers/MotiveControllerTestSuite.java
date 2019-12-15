package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.eprojectkbackend.configuration.LocalDateAdapter;
import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.facade.MotivesFacade;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import com.kodilla.eprojectkbackend.services.MotiveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(MotiveController.class)
public class MotiveControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MotiveService motiveService;

    @MockBean
    private MotiveMapper motiveMapper;

    @MockBean
    private MotiveRepository motiveRepository;

    @MockBean
    private MotivesFacade motivesFacade;

    @Test
    public void getEmptyMotivesTest() throws Exception {
        //Given
        List<Motive> motiveListTest = new ArrayList<>();

        when(motiveService.getAllMotive()).thenReturn(motiveListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void getMotivesTest() throws Exception {
        //Given
        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(new Motive(1L, "testMotiveText",
                "testMotiveAuthor", "testMotiveRating", LocalDate.now()));

        List<MotiveDto> motiveDtoListTest = new ArrayList<>();
        motiveDtoListTest.add(new MotiveDto(1L, "testMotiveTextDto",
                "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now()));

        when(motiveService.getAllMotive()).thenReturn(motiveListTest);
        when(motiveMapper.mapToMotiveDtoList(motiveListTest)).thenReturn(motiveDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].motiveID", is(1)))
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveTextDto")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthorDto")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRatingDto")));
        //.andExpect(jsonPath("$[0].motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }


    @Test
    public void getMotiveTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        long motiveIDTest = motiveTest.getMotiveID();

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto", "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());

        when(motiveService.findMotiveByID(motiveIDTest)).thenReturn(motiveTest);
        when(motiveMapper.mapToMotiveDto(motiveTest)).thenReturn(motiveDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotive").contentType(MediaType.APPLICATION_JSON)
                .param("motiveID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motiveID", is(1)))
                .andExpect(jsonPath("$.motiveText", is("testMotiveTextDto")))
                .andExpect(jsonPath("$.motiveAuthor", is("testMotiveAuthorDto")))
                .andExpect(jsonPath("$.motiveRating", is("testMotiveRatingDto")));
        //.andExpect(jsonPath("$.motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMotiveByAuthorTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        String motiveAuthorTest = motiveTest.getMotiveAuthor();

        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(motiveTest);

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto", "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());
        List<MotiveDto> motiveDtoListTest = new ArrayList<>();
        motiveDtoListTest.add(motiveDtoTest);

        when(motiveService.findMotiveByAuthor(motiveAuthorTest)).thenReturn(motiveListTest);
        when(motiveMapper.mapToMotiveDtoList(motiveListTest)).thenReturn(motiveDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotiveByAuthor").contentType(MediaType.APPLICATION_JSON)
                .param("motiveAuthor", "testMotiveAuthor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].motiveID", is(1)))
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveTextDto")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthorDto")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRatingDto")));
        //.andExpect(jsonPath("$.motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMotiveByRatingTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        String motiveRatingTest = motiveTest.getMotiveRating();

        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(motiveTest);

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto", "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());
        List<MotiveDto> motiveDtoListTest = new ArrayList<>();
        motiveDtoListTest.add(motiveDtoTest);

        when(motiveService.findMotiveByRating(motiveRatingTest)).thenReturn(motiveListTest);
        when(motiveMapper.mapToMotiveDtoList(motiveListTest)).thenReturn(motiveDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotiveByRating").contentType(MediaType.APPLICATION_JSON)
                .param("motiveRating", "testMotiveRating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].motiveID", is(1)))
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveTextDto")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthorDto")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRatingDto")));
        //.andExpect(jsonPath("$.motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void deleteMotiveTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        long motiveIDTest = motiveTest.getMotiveID();

        when(motiveService.findMotiveByID(motiveIDTest)).thenReturn(motiveTest);

        //When & Then
        mockMvc.perform(delete("/eprojectk/motive/deleteMotive").contentType(MediaType.APPLICATION_JSON)
                .param("motiveID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllMotivesTest() throws Exception {
        //Given
        doNothing().when(motiveService).deleteAllMotives();

        //When & Then
        mockMvc.perform(delete("/eprojectk/motive/deleteAllMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void countAllMotivesTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor",
                "testMotiveRating", LocalDate.now());
        Motive motiveTest2 = new Motive(2L, "testMotiveText", "testMotiveAuthor",
                "testMotiveRating", LocalDate.now());
        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto",
                "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());
        MotiveDto motiveDtoTest2 = new MotiveDto(2L, "testMotiveTextDto",
                "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());

        long currentMotivesSize = 2;

        when(motiveService.createMotive(motiveTest)).thenReturn(motiveTest);
        when(motiveService.createMotive(motiveTest2)).thenReturn(motiveTest2);
        when(motiveMapper.mapToMotiveDto(motiveTest)).thenReturn(motiveDtoTest);
        when(motiveMapper.mapToMotiveDto(motiveTest2)).thenReturn(motiveDtoTest2);

        when(motiveService.countAllMotives()).thenReturn(currentMotivesSize);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/countAllMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }


    @Test
    public void createMotiveTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor",
                "testMotiveRating", LocalDate.now());

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto",
                "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());

       // when(motiveService.createMotive(motiveTest)).thenReturn(motiveTest);
       // when(motiveMapper.mapToMotiveDto(motiveTest)).thenReturn(motiveDtoTest);

        Gson gson = new GsonBuilder()
              //  .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        given(this.motiveService.createMotive(motiveTest)).willReturn(motiveTest);

        String jsonContent = gson.toJson(motiveDtoTest);

        //When & Then
        mockMvc.perform(post("/eprojectk/motive/createMotive")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

        @Test
        public void updateMotiveTest() throws Exception {
            //Given
            Motive motiveTest = new Motive(1L, "testMotiveText",
                    "testMotiveAuthor", "testMotiveRating", LocalDate.now());
            MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveTextDto",
                    "testMotiveAuthorDto", "testMotiveRatingDto", LocalDate.now());
            long motiveTestID = motiveTest.getMotiveID();

            Motive motiveTestUpdate = new Motive(1L, "testMotiveTextUpdate",
                    "testMotiveAuthorUpdate", "testMotiveRatingUpdate", LocalDate.now());
            MotiveDto motiveTestUpdateDto = new MotiveDto(1L,
                    "testMotiveTextUpdateDto", "testMotiveAuthorUpdateDto", "testMotiveRatingUpdateDto", LocalDate.now());

            when(motiveRepository.findById(motiveTestID)).thenReturn(java.util.Optional.of(motiveTest));
            when(motiveService.updateMotive(motiveTest)).thenReturn(motiveTestUpdate);
            when(motiveMapper.mapToMotiveDto(motiveTestUpdate)).thenReturn(motiveTestUpdateDto);

            //when(motiveMapper.mapToMotive(ArgumentMatchers.any(MotiveDto.class))).thenReturn(motiveTest);
 /*           when(motiveMapper.mapToMotiveDto(motiveTest)).thenReturn(motiveDtoTest);
            when(motiveService.createMotive(motiveTest)).thenReturn(motiveTest);

            when(motiveMapper.mapToMotive(ArgumentMatchers.any(MotiveDto.class))).thenReturn(motiveTestUpdate);
            when(motiveMapper.mapToMotiveDto(motiveTestUpdate)).thenReturn(motiveTestUpdateDto);
            when(motiveService.updateMotive(motiveTest)).thenReturn(motiveTestUpdate);

            given(this.motiveService.createMotive(motiveTest)).willReturn(motiveTest);
            given(this.motiveService.updateMotive(motiveTestUpdate)).willReturn(motiveTestUpdate);*/

            //Gson gson = new Gson();

            Gson gson = new GsonBuilder()
                   // .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            String jsonContent = gson.toJson(motiveTest);

            System.out.println("wartosc jsonContent" + jsonContent);

            //When & Then
            mockMvc.perform(put("/eprojectk/motive/updateMotive")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                  //  .andExpect(MockMvcResultMatchers.jsonPath("$[0].motiveID", is(1)))
                    .andExpect(jsonPath("$.motiveID", is(1)))
                    .andExpect(jsonPath("$.motiveText", is("testMotiveTextUpdateDto")))
                    .andExpect(jsonPath("$.motiveAuthor", is("testMotiveAuthorUpdateDto")))
                    .andExpect(jsonPath("$.motiveRating", is("testMotiveRatingUpdateDto")));
        }




}
