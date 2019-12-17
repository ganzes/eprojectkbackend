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
public class GameDto {

    private long gameID;
    private String gameTitle;
    private String gameDeveloper;
    private String gameRating;
    private LocalDate gameCreated;

    @Override
    public String toString() {
        return "GameDto{" +
                "gameID=" + gameID +
                ", gameTitle='" + gameTitle + '\'' +
                ", gameDeveloper='" + gameDeveloper + '\'' +
                ", gameRating='" + gameRating + '\'' +
                ", gameCreated=" + gameCreated +
                '}';
    }
}
