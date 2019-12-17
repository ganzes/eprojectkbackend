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
@Table(name = "MOVIES")
public class Movie {

    public Movie(long movieID, String movieTitle, String movieDirector, String movieRating, LocalDate movieCreated) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDirector = movieDirector;
        this.movieRating = movieRating;
        this.movieCreated = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID", unique = true)
    private long movieID;

    @Setter
    @Column(name = "MOVIE_TITLE")
    private String movieTitle;

    @Setter
    @Column(name = "MOVIE_DIRECTOR")
    private String movieDirector;

    @Setter
    @Column(name = "MOVIE_RATING")
    private String movieRating;

    @Column(name = "MOVIE_CREATED")
    private LocalDate movieCreated;

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieRating='" + movieRating + '\'' +
                ", movieCreated=" + movieCreated +
                '}';
    }
}