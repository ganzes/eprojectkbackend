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
public class MovieDto {
    private long movieID;
    private String movieTitle;
    private String movieDirector;
    private String movieRating;
    private LocalDate movieCreated;

    @Override
    public String toString() {
        return "MovieDto{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieRating='" + movieRating + '\'' +
                ", movieCreated=" + movieCreated +
                '}';
    }
}
