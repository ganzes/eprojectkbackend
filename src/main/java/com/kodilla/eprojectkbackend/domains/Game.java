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
@Table(name = "GAMES")
public class Game {

    public Game(long gameID, String gameTitle, String gameDeveloper, String gameRating, LocalDate gameCreated) {
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
        this.gameRating = gameRating;
        this.gameCreated = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", unique = true)
    private long gameID;

    @Setter
    @Column(name = "GAME_TITLE")
    private String gameTitle;

    @Setter
    @Column(name = "GAME_AUTHOR")
    private String gameDeveloper;

    @Setter
    @Column(name = "GAME_RATING")
    private String gameRating;

    @Column(name = "GAME_CREATED")
    private LocalDate gameCreated;

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", gameTitle='" + gameTitle + '\'' +
                ", gameDeveloper='" + gameDeveloper + '\'' +
                ", gameRating='" + gameRating + '\'' +
                ", gameCreated=" + gameCreated +
                '}';
    }
}