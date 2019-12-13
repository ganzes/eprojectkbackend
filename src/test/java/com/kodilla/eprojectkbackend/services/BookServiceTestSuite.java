package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.exceptions.BookNotFoundException;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceTestSuite {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void createBookTest() {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());

        //When
        when(bookRepository.save(bookTest)).thenReturn((bookTest));
        bookService.createBook(bookTest);

        //Then
        verify(bookRepository, times(1)).save(bookTest);
    }

    @Test
    public void getAllBookTest() {
        //Given
        List<Book> bookListTest = new ArrayList<>();
        bookListTest.add(new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now()));
        bookListTest.add(new Book(2L, "testBookTitle2", "testBookAuthor2", "testBookRating2", LocalDate.now()));

        //When
        when(bookService.getAllBook()).thenReturn(bookListTest);

        //Then
        assertEquals(2, bookListTest.size());
    }

    @Test
    public void findBookByAuthorTest() {
        //Given
        List<Book> bookListTest = new ArrayList<>();
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());
        bookListTest.add(bookTest);

        String bookTestAuthor = bookTest.getBookAuthor();
        String bookTestText = bookTest.getBookTitle();

        //When
        when(bookRepository.findByBookAuthor(bookTestAuthor)).thenReturn(bookListTest);

        //Then
        assertEquals("testBookTitle", bookTestText);
        assertEquals("testBookAuthor", bookTestAuthor);
    }

    @Test
    public void findBookByIDTest() {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());
        Long bookTestID = bookTest.getBookID();
        Optional<Book> optionalBookTest = Optional.of(bookTest);
        String bookTestText = bookTest.getBookTitle();
        //When
        when(bookRepository.findById(bookTestID)).thenReturn(optionalBookTest);

        //Then
        assertEquals("testBookTitle", bookTestText);
    }

    @Test
    public void findBookByRating() {
        //Given
        List<Book> bookListTest = new ArrayList<>();
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());
        bookListTest.add(bookTest);

        String bookTestRating = bookTest.getBookRating();
        String bookTestText = bookTest.getBookTitle();

        //When
        when(bookRepository.findByBookAuthor(bookTestRating)).thenReturn(bookListTest);

        //Then
        assertEquals("testBookTitle", bookTestText);
        assertEquals("testBookRating", bookTestRating);
    }

    @Test
    public void deleteBookByIDTest() throws BookNotFoundException {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "testBookRating", LocalDate.now());

        Long bookTestID = bookTest.getBookID();

        //When
        when(bookRepository.findById(bookTestID)).thenReturn(Optional.of(bookTest));

        doNothing().when(bookRepository).delete(bookTest);
        bookService.deleteBookByID(bookTestID);

        //Then
        verify(bookRepository, times(1)).delete(bookTest);
    }

    @Test
    public void deleteAllBooks() throws BookNotFoundException {
        //When
        doNothing().when(bookRepository).deleteAll();
        bookService.deleteAllBooks();

        //Then
        verify(bookRepository, times(1)).deleteAll();

    }
}
