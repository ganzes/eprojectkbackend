package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.domains.GameDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameMapperTestSuite {

    @Autowired
    private GameMapper gameMapper;

    @Test
    public void testMapToGameTest() {
        //Given
        GameDto gameDto = new GameDto(1L, "TestGameTitle", "TestGameDeveloper", "TestGameRating", LocalDate.now());
        //When
        Game game = gameMapper.mapToGame(gameDto);
        //Then
        assertEquals("TestGameTitle", game.getGameTitle());
    }

    @Test
    public void testMapToGameDtoTest() {
        //Given
        Game game = new Game(1L, "TestGameTitle", "TestGameDeveloper", "TestGameRating", LocalDate.now());
        //When
        GameDto gameDto = gameMapper.mapToGameDto(game);
        //Then
        assertEquals("TestGameTitle", gameDto.getGameTitle());
    }

    @Test
    public void testMapToGameDtoListTest() {
        //Given
        Game game = new Game(1L, "TestGameTitle", "TestGameDeveloper", "TestGameRating", LocalDate.now());
        Game game2 = new Game(1L, "TestGameTitle2", "TestGameDeveloper2", "TestGameRating2", LocalDate.now());

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game2);
        //When
        List<GameDto> gameDtoList = gameMapper.mapToGameDtoList(gameList);
        //Then
        assertEquals(2, gameDtoList.size());
    }

    @Test
    public void testMapToGameListTest() {
        //Given
        GameDto gameDto = new GameDto(1L, "TestGameTitle", "TestGameDeveloper", "TestGameRating", LocalDate.now());
        GameDto gameDto2 = new GameDto(2L, "TestGameTitle2", "TestGameDeveloper2", "TestGameRating2", LocalDate.now());

        List<GameDto> gameDtoList = new ArrayList<>();
        gameDtoList.add(gameDto);
        gameDtoList.add(gameDto2);
        //When
        List<Game> gameList = gameMapper.mapToGameList(gameDtoList);
        //Then
        assertEquals(2, gameList.size());
    }
}