package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.exceptions.GameNotFoundException;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }

    public Game findGameByID(long gameID) throws GameNotFoundException{
        return gameRepository.findById(gameID).orElseThrow(GameNotFoundException::new);
    }

    public Game createGame(final Game game){
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) throws GameNotFoundException {
        Game updateGame = gameRepository.findById(game.getGameID()).orElseThrow(GameNotFoundException::new);
        updateGame.setGameTitle(game.getGameTitle());
        updateGame.setGameDeveloper(game.getGameDeveloper());
        updateGame.setGameRating(game.getGameRating());

        return gameRepository.save(updateGame);
    }

    public void deleteGameByID(long gameID) throws GameNotFoundException{
        Game deleteGame = gameRepository.findById(gameID).orElseThrow(GameNotFoundException::new);
        gameRepository.delete(deleteGame);
    }

    public void deleteAllGames(){
        gameRepository.deleteAll();
    }

    public List<Game> findGameByDeveloper(String gameDeveloper) {
        return gameRepository.findByGameDeveloper(gameDeveloper);
    }

    public List<Game> findGameByRating(String gameRating) {
        return gameRepository.findByGameRating(gameRating);
    }

    public long countAllGames(){
        return gameRepository.count();
    }
}