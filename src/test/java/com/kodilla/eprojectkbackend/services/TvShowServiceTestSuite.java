package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.exceptions.TvShowNotFoundException;
import com.kodilla.eprojectkbackend.repositories.TvShowRepository;
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
public class TvShowServiceTestSuite {

    @InjectMocks
    private TvShowService tvShowService;

    @Mock
    private TvShowRepository tvShowRepository;

    @Test
    public void createTvShowTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());

        //When
        when(tvShowRepository.save(tvShowTest)).thenReturn((tvShowTest));
        tvShowService.createTvShow(tvShowTest);

        //Then
        verify(tvShowRepository, times(1)).save(tvShowTest);
    }

    @Test
    public void getAllTvShowTest() {
        //Given
        List<TvShow> tvShowListTest = new ArrayList<>();
        tvShowListTest.add(new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now()));
        tvShowListTest.add(new TvShow(2L, "testTvShowTitle2", "testTvShowCategory2", "testTvShowRating2", LocalDate.now()));

        //When
        when(tvShowService.getAllTvShow()).thenReturn(tvShowListTest);

        //Then
        assertEquals(2, tvShowListTest.size());
    }

    @Test
    public void findTvShowByCategoryTest() {
        //Given
        List<TvShow> tvShowListTest = new ArrayList<>();
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        tvShowListTest.add(tvShowTest);

        String tvShowTestCategory = tvShowTest.getTvShowCategory();
        String tvShowTestText = tvShowTest.getTvShowTitle();

        //When
        when(tvShowRepository.findByTvShowCategory(tvShowTestCategory)).thenReturn(tvShowListTest);

        //Then
        assertEquals("testTvShowTitle", tvShowTestText);
        assertEquals("testTvShowCategory", tvShowTestCategory);
    }

    @Test
    public void findTvShowByIDTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        Long tvShowTestID = tvShowTest.getTvShowID();
        Optional<TvShow> optionalTvShowTest = Optional.of(tvShowTest);
        String tvShowTestText = tvShowTest.getTvShowTitle();
        //When
        when(tvShowRepository.findById(tvShowTestID)).thenReturn(optionalTvShowTest);

        //Then
        assertEquals("testTvShowTitle", tvShowTestText);
    }

    @Test
    public void findTvShowByRating() {
        //Given
        List<TvShow> tvShowListTest = new ArrayList<>();
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        tvShowListTest.add(tvShowTest);

        String tvShowTestRating = tvShowTest.getTvShowRating();
        String tvShowTestText = tvShowTest.getTvShowTitle();

        //When
        when(tvShowRepository.findByTvShowCategory(tvShowTestRating)).thenReturn(tvShowListTest);

        //Then
        assertEquals("testTvShowTitle", tvShowTestText);
        assertEquals("testTvShowRating", tvShowTestRating);
    }

    @Test
    public void deleteTvShowByIDTest() throws TvShowNotFoundException {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());

        Long tvShowTestID = tvShowTest.getTvShowID();

        //When
        when(tvShowRepository.findById(tvShowTestID)).thenReturn(Optional.of(tvShowTest));

        doNothing().when(tvShowRepository).delete(tvShowTest);
        tvShowService.deleteTvShowByID(tvShowTestID);

        //Then
        verify(tvShowRepository, times(1)).delete(tvShowTest);
    }

    @Test
    public void deleteAllTvShows() throws TvShowNotFoundException {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());

        //When
        doNothing().when(tvShowRepository).deleteAll();
        tvShowService.deleteAllTvShows();

        //Then
        verify(tvShowRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllTvShowsTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "testTvShowRating", LocalDate.now());
        tvShowRepository.save(tvShowTest);
        long countAllTvShows = tvShowRepository.count();

        //When
        when(tvShowService.countAllTvShows()).thenReturn(countAllTvShows);

        //Then
        verify(tvShowRepository, times(1)).count();
    }
}