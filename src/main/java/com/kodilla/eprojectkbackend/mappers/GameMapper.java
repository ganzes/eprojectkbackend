package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.domains.GameDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    public Game mapToGame(final GameDto gameDto) {
        return new Game(
                gameDto.getGameID(),
                gameDto.getGameTitle(),
                gameDto.getGameDeveloper(),
                gameDto.getGameRating(),
                gameDto.getGameCreated()
        );
    }

    public GameDto mapToGameDto(final Game game) {
        return new GameDto(
                game.getGameID(),
                game.getGameTitle(),
                game.getGameDeveloper(),
                game.getGameRating(),
                game.getGameCreated()
        );
    }

    public List<GameDto> mapToGameDtoList(final List<Game> gameList) {
        return gameList.stream()
                .map(this::mapToGameDto)
                .collect(Collectors.toList());
    }

    public List<Game> mapToGameList(final List<GameDto> gameDtoList) {
        return gameDtoList.stream()
                .map(this::mapToGame)
                .collect(Collectors.toList());
    }
}
