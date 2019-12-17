package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.exceptions.BookNotFoundException;
import com.kodilla.eprojectkbackend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book findBookByID(long bookID) throws BookNotFoundException{
        return bookRepository.findById(bookID).orElseThrow(BookNotFoundException::new);
    }

    public Book createBook(final Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) throws BookNotFoundException {
        Book updateBook = bookRepository.findById(book.getBookID()).orElseThrow(BookNotFoundException::new);
        updateBook.setBookTitle(book.getBookTitle());
        updateBook.setBookAuthor(book.getBookAuthor());
        updateBook.setBookRating(book.getBookRating());

        return bookRepository.save(updateBook);
    }

    public void deleteBookByID(long bookID) throws BookNotFoundException{
        Book deleteBook = bookRepository.findById(bookID).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(deleteBook);
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }

    public List<Book> findBookByAuthor(String bookAuthor) {
        return bookRepository.findByBookAuthor(bookAuthor);
    }

    public List<Book> findBookByRating(String bookRating) {
        return bookRepository.findByBookRating(bookRating);
    }

    public long countAllBooks(){
        return bookRepository.count();
    }
}
