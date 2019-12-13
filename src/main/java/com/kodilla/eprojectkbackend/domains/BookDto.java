package com.kodilla.eprojectkbackend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long bookID;
    private String bookTitle;
    private String bookAuthor;
    private String bookRating;
    private LocalDate bookCreated;

    @Override
    public String toString() {
        return "BookDto{" +
                "bookID=" + bookID +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRating='" + bookRating + '\'' +
                ", bookCreated=" + bookCreated +
                '}';
    }
}
