package com.kodilla.eprojectkbackend.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    public Book(long bookID, String bookTitle, String bookAuthor, String bookRating, LocalDate bookCreated) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookRating = bookRating;
        this.bookCreated = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID", unique = true)
    private long bookID;

    @Setter
    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Setter
    @Column(name = "BOOK_AUTHOR")
    private String bookAuthor;

    @Setter
    @Column(name = "BOOK_RATING")
    private String bookRating;

    @Column(name = "BOOK_CREATED")
    private LocalDate bookCreated;

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRating='" + bookRating + '\'' +
                ", bookCreated=" + bookCreated +
                '}';
    }
}