package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LoveCalculatorController.class)
public class LoveCalculatorControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoveCalculatorController loveCalculatorController;

    @Test
    public void getPercentageTest() throws Exception{
        //Given
        LoveCalculatorDto loveCalculatorDto = new LoveCalculatorDto(1l,"Test", "Test", "Test", "Test");

        //When
        when(loveCalculatorController.getPercentage("Test", "Test")).thenReturn(loveCalculatorDto);

        //Then
        mockMvc.perform(get("/eprojectk/lc")
                .contentType(MediaType.APPLICATION_JSON)
                .param("fname", "Test").param("sname", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname", is("Test")))
                .andExpect(jsonPath("$.sname", is("Test")))
                .andExpect(jsonPath("$.percentage", is("Test")))
                .andExpect(jsonPath("$.result", is("Test")));
    }
}
