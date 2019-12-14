package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.mappers.BookMapper;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import com.kodilla.eprojectkbackend.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getEmptyBook() throws Exception {
        //Given
        //  List<BookDto> bookListDtoTest = new ArrayList<>();
        List<Book> bookListTest = new ArrayList<>();

        when(bookService.getAllBook()).thenReturn(bookListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/book/getBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void createBookTestSuite() {

    }

}