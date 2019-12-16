package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.BookNotFoundException;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookEntityTestSuite {

    @Autowired
    private BookRepository bookRepository;

/*    public void cleanUp() {
        bookRepository.deleteAll(); // for @Before and @After
    }*/

    @Test
    public void bookSaveTest() {
        //Given
        Book bookSaveTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookSaveTest);
        long bookTestID = bookSaveTest.getBookID();

        //Then
        Assert.assertNotEquals(0, bookTestID);

        //CleanUp
        bookRepository.deleteById(bookTestID);
        bookRepository.deleteAll();
    }

    @Test
    public void bookReadTest() throws BookNotFoundException {
        //Given
        Book bookSaveTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookSaveTest);
        long bookTestID = bookSaveTest.getBookID();

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
        Book bookSaveTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookSaveTest);
        long bookTestID = bookSaveTest.getBookID();

        //Then
        Book updateTest = bookRepository.findById(bookTestID).orElseThrow(BookNotFoundException::new);
        updateTest.setBookTitle("testBookTitleUpdated");
        bookRepository.save(updateTest);

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
        Book bookSaveTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookSaveTest);
        long bookTestID = bookSaveTest.getBookID();
        long countBeforeDelete = bookRepository.count();

        //Then
        bookRepository.deleteById(bookTestID);
        long countAfterDelete = bookRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        bookRepository.deleteAll();
    }

    @Test
    public void deleteAllBooks() {
        //Given
        Book bookSaveTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());
        Book bookSaveTest2 = new Book(2L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookSaveTest);
        bookRepository.save(bookSaveTest2);

        long countBeforeDelete = bookRepository.count();

        //Then
        bookRepository.deleteAll();
        long countAfterDelete = bookRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        bookRepository.deleteAll();
    }

    @Test
    public void countAllBooks() {
        //Given
        Book bookCountTest = new Book(1L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());
        Book bookCountTest2 = new Book(2L, "testBookTitle", "testBookAuthor", "9", LocalDate.now());

        //When
        bookRepository.save(bookCountTest);
        long countBookCountTest = bookRepository.count();

        bookRepository.save(bookCountTest2);
        long countBookCountTest2 = bookRepository.count();

        //Then
        Assert.assertEquals(1, countBookCountTest);
        Assert.assertEquals(2, countBookCountTest2);

        //Clean up
        bookRepository.deleteAll();
    }
} 
