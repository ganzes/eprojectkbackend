package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.domains.GameDto;
import com.kodilla.eprojectkbackend.exceptions.GameNotFoundException;
import com.kodilla.eprojectkbackend.mappers.GameMapper;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import com.kodilla.eprojectkbackend.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private GameRepository gameRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @PostMapping(value = "/createGame", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@RequestBody GameDto gameDto) {
        LOGGER.info("Started method createGame in GameController.");

        gameService.createGame(gameMapper.mapToGame(gameDto));

        LOGGER.info("Ended method createGame in GameController.");
    }

    @GetMapping(value = "/getGame")
    public GameDto getGame(@RequestParam Long gameID) throws GameNotFoundException {
        LOGGER.info("Started method getGame in GameController.");

        return gameMapper.mapToGameDto(gameService.findGameByID(gameID));
    }

    @GetMapping(value = "/getGames")
    public List<GameDto> getGames() {
        LOGGER.info("Started method getGames in GameController.");
        LOGGER.info("Ended method getGame in GameController.");

        return gameMapper.mapToGameDtoList(gameService.getAllGame());
    }

    @PutMapping(value = "/updateGame")
    public GameDto updateGame(@RequestBody GameDto gameDto) throws GameNotFoundException {
        LOGGER.info("Started method updateGame in GameController.");

        Optional<Game> book = gameRepository.findById(gameDto.getGameID());
        if (book.isPresent()){
            return gameMapper.mapToGameDto(gameService.updateGame(gameMapper.mapToGame(gameDto)));
        }
        return gameDto;

    }

    @DeleteMapping(value = "/deleteGame")
    public void deleteGame(@RequestParam Long gameID) throws GameNotFoundException {
        LOGGER.info("Started method deleteGame in GameController.");

        gameService.deleteGameByID(gameID);

        LOGGER.info("Ended method deleteGame in GameController.");
    }

    @DeleteMapping(value = "/deleteAllGames")
    public void deleteAllGames() {
        LOGGER.info("Started method deleteAllGames in GameController.");

        gameService.deleteAllGames();

        LOGGER.info("Ended method deleteAllGames in GameController.");
    }

    @GetMapping(value = "/getGameByDeveloper")
    public List<GameDto> getGameByDeveloper(@RequestParam String gameDeveloper) {
        LOGGER.info("Started method getGameByDeveloper in GameController.");

        return gameMapper.mapToGameDtoList(gameService.findGameByDeveloper(gameDeveloper));
    }

    @GetMapping(value = "/getGameByRating")
    public List<GameDto> getGameByRating(@RequestParam String gameRating) {
        LOGGER.info("Started method getGameByRating in GameController.");

        return gameMapper.mapToGameDtoList(gameService.findGameByRating(gameRating));
    }

    @GetMapping(value = "/countAllGames")
    public Long countAllGames() {
        LOGGER.info("Started method countAllGames in GameController.");
        long allGames = gameService.countAllGames();

        LOGGER.info("Ended method countAllGames in GameController.");
        return allGames;
    }
}