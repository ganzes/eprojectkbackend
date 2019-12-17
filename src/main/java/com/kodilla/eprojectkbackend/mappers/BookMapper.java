package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getBookID(),
                bookDto.getBookTitle(),
                bookDto.getBookAuthor(),
                bookDto.getBookRating(),
                bookDto.getBookCreated()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getBookID(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookRating(),
                book.getBookCreated()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

    public List<Book> mapToBookList(final List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::mapToBook)
                .collect(Collectors.toList());
    }
}