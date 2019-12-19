package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.BookDto;
import com.kodilla.eprojectkbackend.exceptions.BookNotFoundException;
import com.kodilla.eprojectkbackend.mappers.BookMapper;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import com.kodilla.eprojectkbackend.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @PostMapping(value = "/createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto)  {
        LOGGER.info("Started method createBook in BookController.");

        bookService.createBook(bookMapper.mapToBook(bookDto));

        LOGGER.info("Ended method createBook in BookController.");
    }

    @GetMapping(value = "/getBook")
    public BookDto getBook(@RequestParam Long bookID) throws BookNotFoundException {
        LOGGER.info("Started method getBook in BookController.");
        return bookMapper.mapToBookDto(bookService.findBookByID(bookID));
    }

    @GetMapping(value = "/getBooks")
    public List<BookDto> getBooks() {
        LOGGER.info("Started method getBooks in BookController.");
        LOGGER.info("Ended method getBook in BookController.");

        return bookMapper.mapToBookDtoList(bookService.getAllBook());
    }

/*    @PutMapping(value = "/updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        LOGGER.info("Started method updateBook in BookController.");

        Book book = bookRepository.findById(bookDto.getBookID()).orElseThrow(BookNotFoundException::new);
        book.setBookTitle(bookDto.getBookTitle());
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookRating(bookDto.getBookRating());
        Book updateBook = bookService.updateBook(book);

        LOGGER.info("Ended method deleteBook in BookController.");

        return bookMapper.mapToBookDto(updateBook);
    }*/

/*    @PutMapping(value = "/updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        LOGGER.info("Started method updateBook in BookController.");

        Optional<Book> book = bookRepository.findById(bookDto.getBookID());
        if (book.isPresent()){
            bookMapper.mapToBookDto(bookService.updateBook(bookMapper.mapToBook(bookDto)));
        }

        LOGGER.info("Ended method deleteBook in BookController.");

        return bookDto;
    }*/

    @PutMapping(value = "/updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        LOGGER.info("Started method updateBook in BookController.");
        LOGGER.info("Ended method deleteBook in BookController.");

        return bookMapper.mapToBookDto(bookService.updateBook(bookMapper.mapToBook(bookDto)));
    }



    @DeleteMapping(value = "/deleteBook")
    public void deleteBook(@RequestParam Long bookID) throws BookNotFoundException {
        LOGGER.info("Started method deleteBook in BookController.");

        bookService.deleteBookByID(bookID);

        LOGGER.info("Ended method deleteBook in BookController.");
    }

    @DeleteMapping(value = "/deleteAllBooks")
    public void deleteAllBooks() {
        LOGGER.info("Started method deleteAllBooks in BookController.");

        bookService.deleteAllBooks();

        LOGGER.info("Ended method deleteAllBooks in BookController.");
    }

    @GetMapping(value = "/getBookByAuthor")
    public List<BookDto> getBookByAuthor(@RequestParam String bookAuthor) {
        LOGGER.info("Started method getBookByAuthor in BookController.");

        return bookMapper.mapToBookDtoList(bookService.findBookByAuthor(bookAuthor));
    }

    @GetMapping(value = "/getBookByRating")
    public List<BookDto> getBookByRating(@RequestParam String bookRating) {
        LOGGER.info("Started method getBookByRating in BookController.");

        return bookMapper.mapToBookDtoList(bookService.findBookByRating(bookRating));
    }

    @GetMapping(value = "/countAllBooks")
    public Long countAllBooks() {
        LOGGER.info("Started method countAllBooks in BookController.");
        long allBooks = bookService.countAllBooks();

        LOGGER.info("Ended method countAllBooks in BookController.");
        return allBooks;
    }
}
