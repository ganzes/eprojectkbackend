package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
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
import static org.mockito.Mockito.when;
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
    public void getEmptyMotive() throws Exception {
        //Given
        List<MotiveDto> motiveListDtoTest = new ArrayList<>();
        List<Motive> motiveListTest = new ArrayList<>();

        when(motiveService.getAllMotive()).thenReturn(motiveListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/motive/getMotives").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void createMotiveTestSuite() {

    }

}
