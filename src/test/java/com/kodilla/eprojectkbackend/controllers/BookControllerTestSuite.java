package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
import com.kodilla.eprojectkbackend.domains.*;
import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.BookDto;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void getEmptyBooksTest() throws Exception {
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
    public void getBooksTest() throws Exception {
        //Given
        List<Book> bookListTest = new ArrayList<>();
        bookListTest.add(new Book(1L,"testBookTitle",
                "testBookAuthor", "testBookRating", LocalDate.now()));

        List<BookDto> bookDtoListTest = new ArrayList<>();
        bookDtoListTest.add(new BookDto(1L,"testBookTitleDto",
                "testBookAuthorDto", "testBookRatingDto", LocalDate.now()));

        when(bookService.getAllBook()).thenReturn(bookListTest);
        when(bookMapper.mapToBookDtoList(bookListTest)).thenReturn(bookDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/book/getBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookID", is(1)))
                .andExpect(jsonPath("$[0].bookTitle", is("testBookTitleDto")))
                .andExpect(jsonPath("$[0].bookAuthor", is("testBookAuthorDto")))
                .andExpect(jsonPath("$[0].bookRating", is("testBookRatingDto")));
        //.andExpect(jsonPath("$[0].bookCreated", is("2019-12-14"))); for testing purposes, enter current date
    }
    @Test
    public void getBookTest() throws Exception{
        //Given
        Book bookTest = new Book(1L,"testBookTitle","testBookAuthor", "testBookRating", LocalDate.now());
        long bookIDTest = bookTest.getBookID();

        BookDto bookDtoTest = new BookDto(1L,"testBookTitleDto","testBookAuthorDto", "testBookRatingDto", LocalDate.now());

        when(bookService.findBookByID(bookIDTest)).thenReturn(bookTest);
        when(bookMapper.mapToBookDto(bookTest)).thenReturn(bookDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/book/getBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookID", is(1)))
                .andExpect(jsonPath("$.bookTitle", is("testBookTitleDto")))
                .andExpect(jsonPath("$.bookAuthor", is("testBookAuthorDto")))
                .andExpect(jsonPath("$.bookRating", is("testBookRatingDto")));
        //.andExpect(jsonPath("$.bookCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getBookByAuthorTest() throws Exception {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());
        String bookAuthorTest = bookTest.getBookAuthor();

        List<Book> bookListTest = new ArrayList<>();
        bookListTest.add(bookTest);

        BookDto bookDtoTest = new BookDto(1L, "testBookTitleDto", "testBookAuthorDto", "testBookRatingDto", LocalDate.now());
        List<BookDto> bookDtoListTest = new ArrayList<>();
        bookDtoListTest.add(bookDtoTest);

        when(bookService.findBookByAuthor(bookAuthorTest)).thenReturn(bookListTest);
        when(bookMapper.mapToBookDtoList(bookListTest)).thenReturn(bookDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/book/getBookByAuthor").contentType(MediaType.APPLICATION_JSON)
                .param("bookAuthor", "testBookAuthor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookID", is(1)))
                .andExpect(jsonPath("$[0].bookTitle", is("testBookTitleDto")))
                .andExpect(jsonPath("$[0].bookAuthor", is("testBookAuthorDto")))
                .andExpect(jsonPath("$[0].bookRating", is("testBookRatingDto")));
        //.andExpect(jsonPath("$.bookCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getBookByRatingTest() throws Exception {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());
        String bookRatingTest = bookTest.getBookRating();

        List<Book> bookListTest = new ArrayList<>();
        bookListTest.add(bookTest);

        BookDto bookDtoTest = new BookDto(1L, "testBookTitleDto", "testBookAuthorDto", "testBookRatingDto", LocalDate.now());
        List<BookDto> bookDtoListTest = new ArrayList<>();
        bookDtoListTest.add(bookDtoTest);

        when(bookService.findBookByRating(bookRatingTest)).thenReturn(bookListTest);
        when(bookMapper.mapToBookDtoList(bookListTest)).thenReturn(bookDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/book/getBookByRating").contentType(MediaType.APPLICATION_JSON)
                .param("bookRating", "testBookRating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookID", is(1)))
                .andExpect(jsonPath("$[0].bookTitle", is("testBookTitleDto")))
                .andExpect(jsonPath("$[0].bookAuthor", is("testBookAuthorDto")))
                .andExpect(jsonPath("$[0].bookRating", is("testBookRatingDto")));
        //.andExpect(jsonPath("$.bookCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void deleteBookTest() throws Exception {
        //Given
        Book bookTest = new Book(1L,"testBookTitle","testBookAuthor", "testBookRating", LocalDate.now());
        long bookIDTest = bookTest.getBookID();

        when(bookService.findBookByID(bookIDTest)).thenReturn(bookTest);

        //When & Then
        mockMvc.perform(delete("/eprojectk/book/deleteBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllBooksTest() throws Exception {
        //Given
        doNothing().when(bookService).deleteAllBooks();

        //When & Then
        mockMvc.perform(delete("/eprojectk/book/deleteAllBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBookTest() throws Exception {
        //Given
        Book bookTest = new Book(1L,"testBookTitle","testBookAuthor", "testBookRating", LocalDate.now());
        BookDto bookDtoTest = new BookDto();

        when(bookMapper.mapToBook(bookDtoTest)).thenReturn(bookTest);
        when(bookService.createBook(bookTest)).thenReturn(bookTest);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDtoTest);

        //When & Then
        mockMvc.perform(post("/eprojectk/book/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}