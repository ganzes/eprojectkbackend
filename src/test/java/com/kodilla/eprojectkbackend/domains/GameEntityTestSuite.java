package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.GameNotFoundException;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameEntityTestSuite {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void gameSaveTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        Game savedgameTest = gameRepository.save(gameTest);
        long gameTestID = savedgameTest.getGameID();

        //Then
        Assert.assertNotEquals(0, gameTestID);
        Assert.assertEquals(1L, gameTestID);

        //CleanUp
        gameRepository.deleteById(gameTestID);
        gameRepository.deleteAll();
    }

    @Test
    public void gameReadTest() throws GameNotFoundException {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        Game savedGame = gameRepository.save(gameTest);
        long gameTestID = savedGame.getGameID();

        //Then
        Game resultReadTest = gameRepository.findById(gameTestID).orElseThrow(GameNotFoundException::new);

        Assert.assertEquals("testGameTitle", resultReadTest.getGameTitle());
        Assert.assertEquals("testGameDeveloper", resultReadTest.getGameDeveloper());

        //CleanUp
        gameRepository.deleteById(gameTestID);
        gameRepository.deleteAll();
    }

    @Test
    public void gameUpdateTest() throws GameNotFoundException {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        gameRepository.save(gameTest);
        long gameTestID = gameTest.getGameID();

        //Then
        Game updatedGameTest = gameRepository.findById(gameTestID).orElseThrow(GameNotFoundException::new);
        updatedGameTest.setGameTitle("testGameTitleUpdated");
        gameRepository.save(updatedGameTest);

        Game resultUpdateTest = gameRepository.findById(gameTestID).orElseThrow(GameNotFoundException::new);

        //Then
        Assert.assertEquals("testGameTitleUpdated", resultUpdateTest.getGameTitle());

        //CleanUp
        gameRepository.deleteById(gameTestID);
        gameRepository.deleteAll();
    }

    @Test
    public void gameDeleteTest() throws GameNotFoundException {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        Game savedGame = gameRepository.save(gameTest);
        long gameTestID = savedGame.getGameID();
        long countBeforeDelete = gameRepository.count();

        //Then
        gameRepository.deleteById(gameTestID);
        long countAfterDelete = gameRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        gameRepository.deleteAll();
    }

    @Test
    public void deleteAllGamesTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());
        Game gameTest2 = new Game(2L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        gameRepository.save(gameTest);
        gameRepository.save(gameTest2);

        long countBeforeDelete = gameRepository.count();

        //Then
        gameRepository.deleteAll();
        long countAfterDelete = gameRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        gameRepository.deleteAll();
    }

    @Test
    public void countAllGamesTest() {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());
        Game gameTest2 = new Game(2L, "testGameTitle", "testGameDeveloper", "9", LocalDate.now());

        //When
        gameRepository.save(gameTest);
        long countGameCountTest = gameRepository.count();

        gameRepository.save(gameTest2);
        long countGameCountTest2 = gameRepository.count();

        //Then
        Assert.assertEquals(1, countGameCountTest);
        Assert.assertEquals(2, countGameCountTest2);

        //Clean up
        gameRepository.deleteAll();
    }
}