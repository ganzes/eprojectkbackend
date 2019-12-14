package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.facade.MotivesFacade;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import com.kodilla.eprojectkbackend.services.MotiveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        //  List<MotiveDto> motiveListDtoTest = new ArrayList<>();
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
        motiveDtoListTest.add(new MotiveDto(1L, "testMotiveText",
                "testMotiveAuthor", "testMotiveRating", LocalDate.now()));

        when(motiveService.getAllMotive()).thenReturn(motiveListTest);
        when(motiveMapper.mapToMotiveDtoList(motiveListTest)).thenReturn(motiveDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].motiveID", is(1)))
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveText")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthor")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRating")));
        //.andExpect(jsonPath("$[0].motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }


    @Test
    public void getMotiveTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        long motiveIDTest = motiveTest.getMotiveID();

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());

        when(motiveService.findMotiveByID(motiveIDTest)).thenReturn(motiveTest);
        when(motiveMapper.mapToMotiveDto(motiveTest)).thenReturn(motiveDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotive").contentType(MediaType.APPLICATION_JSON)
                .param("motiveID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motiveID", is(1)))
                .andExpect(jsonPath("$.motiveText", is("testMotiveText")))
                .andExpect(jsonPath("$.motiveAuthor", is("testMotiveAuthor")))
                .andExpect(jsonPath("$.motiveRating", is("testMotiveRating")));
        //.andExpect(jsonPath("$.motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMotiveByAuthorTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        String motiveAuthorTest = motiveTest.getMotiveAuthor();

        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(motiveTest);

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
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
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveText")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthor")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRating")));
        //.andExpect(jsonPath("$.motiveCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getMotiveByRatingTest() throws Exception {
        //Given
        Motive motiveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
        String motiveRatingTest = motiveTest.getMotiveRating();

        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(motiveTest);

        MotiveDto motiveDtoTest = new MotiveDto(1L, "testMotiveText", "testMotiveAuthor", "testMotiveRating", LocalDate.now());
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
                .andExpect(jsonPath("$[0].motiveText", is("testMotiveText")))
                .andExpect(jsonPath("$[0].motiveAuthor", is("testMotiveAuthor")))
                .andExpect(jsonPath("$[0].motiveRating", is("testMotiveRating")));
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
    public void deleteAllMotives() throws Exception {
        //Given
        doNothing().when(motiveService).deleteAllMotives();

        //When & Then
        mockMvc.perform(delete("/eprojectk/motive/deleteAllMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
