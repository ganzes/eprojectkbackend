package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.TvShowNotFoundException;
import com.kodilla.eprojectkbackend.repositories.TvShowRepository;
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
public class TvShowEntityTestSuite {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Test
    public void tvShowSaveTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        TvShow savedtvShowTest = tvShowRepository.save(tvShowTest);
        long tvShowTestID = savedtvShowTest.getTvShowID();

        //Then
        Assert.assertNotEquals(0, tvShowTestID);
        Assert.assertEquals(1L, tvShowTestID);

        //CleanUp
        tvShowRepository.deleteById(tvShowTestID);
        tvShowRepository.deleteAll();
    }

    @Test
    public void tvShowReadTest() throws TvShowNotFoundException {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        TvShow savedTvShow = tvShowRepository.save(tvShowTest);
        long tvShowTestID = savedTvShow.getTvShowID();

        //Then
        TvShow resultReadTest = tvShowRepository.findById(tvShowTestID).orElseThrow(TvShowNotFoundException::new);

        Assert.assertEquals("testTvShowTitle", resultReadTest.getTvShowTitle());
        Assert.assertEquals("testTvShowCategory", resultReadTest.getTvShowCategory());

        //CleanUp
        tvShowRepository.deleteById(tvShowTestID);
        tvShowRepository.deleteAll();
    }

    @Test
    public void tvShowUpdateTest() throws TvShowNotFoundException {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        tvShowRepository.save(tvShowTest);
        long tvShowTestID = tvShowTest.getTvShowID();

        //Then
        TvShow updatedTvShowTest = tvShowRepository.findById(tvShowTestID).orElseThrow(TvShowNotFoundException::new);
        updatedTvShowTest.setTvShowTitle("testTvShowTitleUpdated");
        tvShowRepository.save(updatedTvShowTest);

        TvShow resultUpdateTest = tvShowRepository.findById(tvShowTestID).orElseThrow(TvShowNotFoundException::new);

        //Then
        Assert.assertEquals("testTvShowTitleUpdated", resultUpdateTest.getTvShowTitle());

        //CleanUp
        tvShowRepository.deleteById(tvShowTestID);
        tvShowRepository.deleteAll();
    }

    @Test
    public void tvShowDeleteTest() throws TvShowNotFoundException {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        TvShow savedTvShow = tvShowRepository.save(tvShowTest);
        long tvShowTestID = savedTvShow.getTvShowID();
        long countBeforeDelete = tvShowRepository.count();

        //Then
        tvShowRepository.deleteById(tvShowTestID);
        long countAfterDelete = tvShowRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        tvShowRepository.deleteAll();
    }

    @Test
    public void deleteAllTvShowsTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());
        TvShow tvShowTest2 = new TvShow(2L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        tvShowRepository.save(tvShowTest);
        tvShowRepository.save(tvShowTest2);

        long countBeforeDelete = tvShowRepository.count();

        //Then
        tvShowRepository.deleteAll();
        long countAfterDelete = tvShowRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        tvShowRepository.deleteAll();
    }

    @Test
    public void countAllTvShowsTest() {
        //Given
        TvShow tvShowTest = new TvShow(1L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());
        TvShow tvShowTest2 = new TvShow(2L, "testTvShowTitle", "testTvShowCategory", "9", LocalDate.now());

        //When
        tvShowRepository.save(tvShowTest);
        long countTvShowCountTest = tvShowRepository.count();

        tvShowRepository.save(tvShowTest2);
        long countTvShowCountTest2 = tvShowRepository.count();

        //Then
        Assert.assertEquals(1, countTvShowCountTest);
        Assert.assertEquals(2, countTvShowCountTest2);

        //Clean up
        tvShowRepository.deleteAll();
    }
} 