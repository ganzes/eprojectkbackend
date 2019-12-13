package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testMapToBook() {
        //Given
        BookDto bookDto = new BookDto(1L, "TestBookTitle", "TestBookAuthor", "TestBookRating", LocalDate.now());
        //When
        Book book = bookMapper.mapToBook(bookDto);
        //Then
        assertEquals("TestBookTitle", book.getBookTitle());
    }

    @Test
    public void testMapToBookDto() {
        //Given
        Book book = new Book(1L,"TestBookTitle", "TestBookAuthor", "TestBookRating", LocalDate.now());
        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);
        //Then
        assertEquals("TestBookTitle", bookDto.getBookTitle());
    }

    @Test
    public void testMapToBookDtoList() {
        //Given
        Book book = new Book(1L,"TestBookTitle", "TestBookAuthor", "TestBookRating", LocalDate.now());
        Book book2 = new Book(1L,"TestBookTitle2", "TestBookAuthor2", "TestBookRating2", LocalDate.now());

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        //When
        List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(bookList);
        //Then
        assertEquals(2, bookDtoList.size());
    }

    @Test
    public void testMapToBookList() {
        //Given
        BookDto bookDto = new BookDto(1L, "TestBookTitle", "TestBookAuthor", "TestBookRating", LocalDate.now());
        BookDto bookDto2 = new BookDto(2L, "TestBookTitle2", "TestBookAuthor2", "TestBookRating2", LocalDate.now());

        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto);
        bookDtoList.add(bookDto2);
        //When
        List<Book> bookList = bookMapper.mapToBookList(bookDtoList);
        //Then
        assertEquals(2, bookList.size());
    }
}