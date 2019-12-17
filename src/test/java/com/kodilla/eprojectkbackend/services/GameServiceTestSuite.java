package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.exceptions.GameNotFoundException;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GameServiceTestSuite {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    public void createGameTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());

        //When
        when(gameRepository.save(gameTest)).thenReturn((gameTest));
        gameService.createGame(gameTest);

        //Then
        verify(gameRepository, times(1)).save(gameTest);
    }

    @Test
    public void getAllGameTest() {
        //Given
        List<Game> gameListTest = new ArrayList<>();
        gameListTest.add(new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now()));
        gameListTest.add(new Game(2L, "testGameTitle2", "testGameDeveloper2", "testGameRating2", LocalDate.now()));

        //When
        when(gameService.getAllGame()).thenReturn(gameListTest);

        //Then
        assertEquals(2, gameListTest.size());
    }

    @Test
    public void findGameByDeveloperTest() {
        //Given
        List<Game> gameListTest = new ArrayList<>();
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        gameListTest.add(gameTest);

        String gameTestDeveloper = gameTest.getGameDeveloper();
        String gameTestText = gameTest.getGameTitle();

        //When
        when(gameRepository.findByGameDeveloper(gameTestDeveloper)).thenReturn(gameListTest);

        //Then
        assertEquals("testGameTitle", gameTestText);
        assertEquals("testGameDeveloper", gameTestDeveloper);
    }

    @Test
    public void findGameByIDTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        Long gameTestID = gameTest.getGameID();
        Optional<Game> optionalGameTest = Optional.of(gameTest);
        String gameTestText = gameTest.getGameTitle();
        //When
        when(gameRepository.findById(gameTestID)).thenReturn(optionalGameTest);

        //Then
        assertEquals("testGameTitle", gameTestText);
    }

    @Test
    public void findGameByRating() {
        //Given
        List<Game> gameListTest = new ArrayList<>();
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        gameListTest.add(gameTest);

        String gameTestRating = gameTest.getGameRating();
        String gameTestText = gameTest.getGameTitle();

        //When
        when(gameRepository.findByGameDeveloper(gameTestRating)).thenReturn(gameListTest);

        //Then
        assertEquals("testGameTitle", gameTestText);
        assertEquals("testGameRating", gameTestRating);
    }

    @Test
    public void deleteGameByIDTest() throws GameNotFoundException {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());

        Long gameTestID = gameTest.getGameID();

        //When
        when(gameRepository.findById(gameTestID)).thenReturn(Optional.of(gameTest));

        doNothing().when(gameRepository).delete(gameTest);
        gameService.deleteGameByID(gameTestID);

        //Then
        verify(gameRepository, times(1)).delete(gameTest);
    }

    @Test
    public void deleteAllGames() throws GameNotFoundException {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());

        //When
        doNothing().when(gameRepository).deleteAll();
        gameService.deleteAllGames();

        //Then
        verify(gameRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllGamesTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        gameRepository.save(gameTest);
        long countAllGames = gameRepository.count();

        //When
        when(gameService.countAllGames()).thenReturn(countAllGames);

        //Then
        verify(gameRepository, times(1)).count();
    }
}
