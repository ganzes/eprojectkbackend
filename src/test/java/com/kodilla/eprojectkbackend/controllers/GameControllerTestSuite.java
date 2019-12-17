package com.kodilla.eprojectkbackend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.eprojectkbackend.configuration.LocalDateAdapter;
import com.kodilla.eprojectkbackend.domains.Game;
import com.kodilla.eprojectkbackend.domains.GameDto;
import com.kodilla.eprojectkbackend.mappers.GameMapper;
import com.kodilla.eprojectkbackend.repositories.GameRepository;
import com.kodilla.eprojectkbackend.services.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameMapper gameMapper;

    @MockBean
    private GameRepository gameRepository;

    @Test
    public void getEmptyGamesTest() throws Exception {
        //Given
        //  List<GameDto> gameListDtoTest = new ArrayList<>();
        List<Game> gameListTest = new ArrayList<>();

        when(gameService.getAllGame()).thenReturn(gameListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/getGames").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void getGamesTest() throws Exception {
        //Given
        List<Game> gameListTest = new ArrayList<>();
        gameListTest.add(new Game(1L, "testGameTitle",
                "testGameDeveloper", "testGameRating", LocalDate.now()));

        List<GameDto> gameDtoListTest = new ArrayList<>();
        gameDtoListTest.add(new GameDto(1L, "testGameTitleDto",
                "testGameDeveloperDto", "testGameRatingDto", LocalDate.now()));

        when(gameService.getAllGame()).thenReturn(gameListTest);
        when(gameMapper.mapToGameDtoList(gameListTest)).thenReturn(gameDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/getGames").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gameID", is(1)))
                .andExpect(jsonPath("$[0].gameTitle", is("testGameTitleDto")))
                .andExpect(jsonPath("$[0].gameDeveloper", is("testGameDeveloperDto")))
                .andExpect(jsonPath("$[0].gameRating", is("testGameRatingDto")));
        //.andExpect(jsonPath("$[0].gameCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getGameTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        long gameIDTest = gameTest.getGameID();

        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto", "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());

        when(gameService.findGameByID(gameIDTest)).thenReturn(gameTest);
        when(gameMapper.mapToGameDto(gameTest)).thenReturn(gameDtoTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/getGame").contentType(MediaType.APPLICATION_JSON)
                .param("gameID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameID", is(1)))
                .andExpect(jsonPath("$.gameTitle", is("testGameTitleDto")))
                .andExpect(jsonPath("$.gameDeveloper", is("testGameDeveloperDto")))
                .andExpect(jsonPath("$.gameRating", is("testGameRatingDto")));
        //.andExpect(jsonPath("$.gameCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getGameByDeveloperTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        String gameDeveloperTest = gameTest.getGameDeveloper();

        List<Game> gameListTest = new ArrayList<>();
        gameListTest.add(gameTest);

        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto", "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());
        List<GameDto> gameDtoListTest = new ArrayList<>();
        gameDtoListTest.add(gameDtoTest);

        when(gameService.findGameByDeveloper(gameDeveloperTest)).thenReturn(gameListTest);
        when(gameMapper.mapToGameDtoList(gameListTest)).thenReturn(gameDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/getGameByDeveloper").contentType(MediaType.APPLICATION_JSON)
                .param("gameDeveloper", "testGameDeveloper"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gameID", is(1)))
                .andExpect(jsonPath("$[0].gameTitle", is("testGameTitleDto")))
                .andExpect(jsonPath("$[0].gameDeveloper", is("testGameDeveloperDto")))
                .andExpect(jsonPath("$[0].gameRating", is("testGameRatingDto")));
        //.andExpect(jsonPath("$.gameCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void getGameByRatingTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        String gameRatingTest = gameTest.getGameRating();

        List<Game> gameListTest = new ArrayList<>();
        gameListTest.add(gameTest);

        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto", "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());
        List<GameDto> gameDtoListTest = new ArrayList<>();
        gameDtoListTest.add(gameDtoTest);

        when(gameService.findGameByRating(gameRatingTest)).thenReturn(gameListTest);
        when(gameMapper.mapToGameDtoList(gameListTest)).thenReturn(gameDtoListTest);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/getGameByRating").contentType(MediaType.APPLICATION_JSON)
                .param("gameRating", "testGameRating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gameID", is(1)))
                .andExpect(jsonPath("$[0].gameTitle", is("testGameTitleDto")))
                .andExpect(jsonPath("$[0].gameDeveloper", is("testGameDeveloperDto")))
                .andExpect(jsonPath("$[0].gameRating", is("testGameRatingDto")));
        //.andExpect(jsonPath("$.gameCreated", is("2019-12-14"))); for testing purposes, enter current date
    }

    @Test
    public void deleteGameTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper", "testGameRating", LocalDate.now());
        long gameIDTest = gameTest.getGameID();

        when(gameService.findGameByID(gameIDTest)).thenReturn(gameTest);

        //When & Then
        mockMvc.perform(delete("/eprojectk/game/deleteGame").contentType(MediaType.APPLICATION_JSON)
                .param("gameID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllGamesTest() throws Exception {
        //Given
        doNothing().when(gameService).deleteAllGames();

        //When & Then
        mockMvc.perform(delete("/eprojectk/game/deleteAllGames").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createGameTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper",
                "testGameRating", LocalDate.now());

        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto",
                "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());

        Gson gson = new GsonBuilder()
                //  .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        given(this.gameService.createGame(gameTest)).willReturn(gameTest);

        String jsonContent = gson.toJson(gameDtoTest);

        //When & Then
        mockMvc.perform(post("/eprojectk/game/createGame")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void countAllGamesTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle", "testGameDeveloper",
                "testGameRating", LocalDate.now());
        Game gameTest2 = new Game(2L, "testGameTitle", "testGameDeveloper",
                "testGameRating", LocalDate.now());
        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto",
                "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());
        GameDto gameDtoTest2 = new GameDto(2L, "testGameTitleDto",
                "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());

        long currentGamesSize = 2;

        when(gameService.createGame(gameTest)).thenReturn(gameTest);
        when(gameService.createGame(gameTest2)).thenReturn(gameTest2);
        when(gameMapper.mapToGameDto(gameTest)).thenReturn(gameDtoTest);
        when(gameMapper.mapToGameDto(gameTest2)).thenReturn(gameDtoTest2);

        when(gameService.countAllGames()).thenReturn(currentGamesSize);

        //When & Then
        mockMvc.perform(get("/eprojectk/game/countAllGames").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    public void updateGameTest() throws Exception {
        //Given
        Game gameTest = new Game(1L, "testGameTitle",
                "testGameDeveloper", "testGameRating", LocalDate.now());
        GameDto gameDtoTest = new GameDto(1L, "testGameTitleDto",
                "testGameDeveloperDto", "testGameRatingDto", LocalDate.now());
        long gameTestID = gameTest.getGameID();

        Game gameTestUpdate = new Game(1L, "testGameTitleUpdate",
                "testGameDeveloperUpdate", "testGameRatingUpdate", LocalDate.now());
        GameDto gameTestUpdateDto = new GameDto(1L,
                "testGameTitleUpdateDto", "testGameDeveloperUpdateDto", "testGameRatingUpdateDto", LocalDate.now());

        when(gameRepository.findById(gameTestID)).thenReturn(java.util.Optional.of(gameTest));
        when(gameService.updateGame(gameTest)).thenReturn(gameTestUpdate);
        when(gameMapper.mapToGameDto(gameTestUpdate)).thenReturn(gameTestUpdateDto);

        Gson gson = new GsonBuilder()
                // .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(gameTest);

        System.out.println("wartosc jsonContent" + jsonContent);

        //When & Then
        mockMvc.perform(put("/eprojectk/game/updateGame")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.gameID", is(1)))
                .andExpect(jsonPath("$.gameTitle", is("testGameTitleUpdateDto")))
                .andExpect(jsonPath("$.gameDeveloper", is("testGameDeveloperUpdateDto")))
                .andExpect(jsonPath("$.gameRating", is("testGameRatingUpdateDto")));
    }
}