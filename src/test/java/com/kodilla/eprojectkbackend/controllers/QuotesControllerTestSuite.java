package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.QuotesDto;
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
@WebMvcTest(QuotesController.class)
public class QuotesControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public QuotesController quotesController;

    @Test
    public void getQuoteByKeywordTest() throws Exception {
        //Given
        QuotesDto quotesDtoTest = new QuotesDto("testMessage", "testAuthor", "testKeywords",
                "testProfession", "testNationality", "testAuthorBirth", "testAuthorDeath");

        //Then
        when(quotesController.getQuoteByKeyword("Test")).thenReturn(quotesDtoTest);

        //Then
        mockMvc.perform(get("/eprojectk/quotes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("keyword", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("testMessage")))
                .andExpect(jsonPath("$.author", is("testAuthor")))
                .andExpect(jsonPath("$.keywords", is("testKeywords")))
                .andExpect(jsonPath("$.profession", is("testProfession")))
                .andExpect(jsonPath("$.nationality", is("testNationality")))
                .andExpect(jsonPath("$.authorBirth", is("testAuthorBirth")))
                .andExpect(jsonPath("$.authorDeath", is("testAuthorDeath")));
    }

    @Test
    public void getRandomQuoteTest() throws Exception {
        //Given
        QuotesDto quotesDtoTest = new QuotesDto("testMessage", "testAuthor", "testKeywords",
                "testProfession", "testNationality", "testAuthorBirth", "testAuthorDeath");

        //Then
        when(quotesController.getRandomQuote()).thenReturn(quotesDtoTest);

        //Then
        mockMvc.perform(get("/eprojectk/quotes/getRandom")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("testMessage")))
                .andExpect(jsonPath("$.author", is("testAuthor")))
                .andExpect(jsonPath("$.keywords", is("testKeywords")))
                .andExpect(jsonPath("$.profession", is("testProfession")))
                .andExpect(jsonPath("$.nationality", is("testNationality")))
                .andExpect(jsonPath("$.authorBirth", is("testAuthorBirth")))
                .andExpect(jsonPath("$.authorDeath", is("testAuthorDeath")));
    }

    @Test
    public void getQuoteByAythorTest() throws Exception {
        //Given
        QuotesDto quotesDtoTest = new QuotesDto("testMessage", "testAuthor", "testKeywords",
                "testProfession", "testNationality", "testAuthorBirth", "testAuthorDeath");

        //Then
        when(quotesController.getQuoteByAuthor("Test")).thenReturn(quotesDtoTest);

        //Then
        mockMvc.perform(get("/eprojectk/quotes/byAuthor")
                .contentType(MediaType.APPLICATION_JSON)
                .param("author", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("testMessage")))
                .andExpect(jsonPath("$.author", is("testAuthor")))
                .andExpect(jsonPath("$.keywords", is("testKeywords")))
                .andExpect(jsonPath("$.profession", is("testProfession")))
                .andExpect(jsonPath("$.nationality", is("testNationality")))
                .andExpect(jsonPath("$.authorBirth", is("testAuthorBirth")))
                .andExpect(jsonPath("$.authorDeath", is("testAuthorDeath")));
    }
}
