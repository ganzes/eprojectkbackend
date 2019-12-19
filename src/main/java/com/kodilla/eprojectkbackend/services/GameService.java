package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.exceptions.GameNotFoundException;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }

    public Game findGameByID(long gameID) throws GameNotFoundException {
        return gameRepository.findById(gameID).orElseThrow(GameNotFoundException::new);
    }

    public Game createGame(final Game game) {
        Optional<Game> gameOptional = gameRepository.findById(game.getGameID());
        if (!gameOptional.isPresent()) {
            return gameRepository.save(game);
        }
        return game;
    }

    public Game updateGame(Game game) {
        Optional<Game> gameOptional = gameRepository.findById(game.getGameID());
        if (gameOptional.isPresent()) {
            return gameRepository.save(game);
        }
        return game;
    }

    public void deleteGameByID(long gameID) throws GameNotFoundException {
        Game deleteGame = gameRepository.findById(gameID).orElseThrow(GameNotFoundException::new);
        gameRepository.delete(deleteGame);
    }

    public void deleteAllGames() {
        gameRepository.deleteAll();
    }

    public List<Game> findGameByDeveloper(String gameDeveloper) {
        return gameRepository.findByGameDeveloper(gameDeveloper);
    }

    public List<Game> findGameByRating(String gameRating) {
        return gameRepository.findByGameRating(gameRating);
    }

    public long countAllGames() {
        return gameRepository.count();
    }
}