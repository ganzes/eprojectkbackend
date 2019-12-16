package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.BookNotFoundException;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookEntityTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookSaveTest() {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        Book savedbookTest = bookRepository.save(bookTest);
        long bookTestID = savedbookTest.getBookID();

        //Then
        Assert.assertNotEquals(0, bookTestID);
        Assert.assertEquals(1L, bookTestID);

        //CleanUp
        bookRepository.deleteById(bookTestID);
        bookRepository.deleteAll();
    }

    @Test
    public void bookReadTest() throws BookNotFoundException {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        Book savedBook = bookRepository.save(bookTest);
        long bookTestID = savedBook.getBookID();

        //Then
        Book resultReadTest = bookRepository.findById(bookTestID).orElseThrow(BookNotFoundException::new);

        Assert.assertEquals("testBookTitle", resultReadTest.getBookTitle());
        Assert.assertEquals("testBookAuthor", resultReadTest.getBookAuthor());

        //CleanUp
        bookRepository.deleteById(bookTestID);
        bookRepository.deleteAll();
    }

    @Test
    public void bookUpdateTest() throws BookNotFoundException {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookTest);
        long bookTestID = bookTest.getBookID();

        //Then
        Book updatedBookTest = bookRepository.findById(bookTestID).orElseThrow(BookNotFoundException::new);
        updatedBookTest.setBookTitle("testBookTitleUpdated");
        bookRepository.save(updatedBookTest);

        Book resultUpdateTest = bookRepository.findById(bookTestID).orElseThrow(BookNotFoundException::new);

        //Then
        Assert.assertEquals("testBookTitleUpdated", resultUpdateTest.getBookTitle());

        //CleanUp
        bookRepository.deleteById(bookTestID);
        bookRepository.deleteAll();
    }

    @Test
    public void bookDeleteTest() throws BookNotFoundException {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        Book savedBook = bookRepository.save(bookTest);
        long bookTestID = savedBook.getBookID();
        long countBeforeDelete = bookRepository.count();

        //Then
        bookRepository.deleteById(bookTestID);
        long countAfterDelete = bookRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        bookRepository.deleteAll();
    }

    @Test
    public void deleteAllBooksTest() {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());
        Book bookTest2 = new Book(2L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookTest);
        bookRepository.save(bookTest2);

        long countBeforeDelete = bookRepository.count();

        //Then
        bookRepository.deleteAll();
        long countAfterDelete = bookRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        bookRepository.deleteAll();
    }

    @Test
    public void countAllBooksTest() {
        //Given
        Book bookTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());
        Book bookTest2 = new Book(2L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookTest);
        long countBookCountTest = bookRepository.count();

        bookRepository.save(bookTest2);
        long countBookCountTest2 = bookRepository.count();

        //Then
        Assert.assertEquals(1, countBookCountTest);
        Assert.assertEquals(2, countBookCountTest2);

        //Clean up
        bookRepository.deleteAll();
    }
} 
