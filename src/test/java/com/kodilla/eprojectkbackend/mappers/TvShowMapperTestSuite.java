package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.domains.TvShowDto;
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
public class TvShowMapperTestSuite {

    @Autowired
    private TvShowMapper tvShowMapper;

    @Test
    public void testMapToTvShowTest() {
        //Given
        TvShowDto tvShowDto = new TvShowDto(1L, "TestTvShowTitle", "TestTvShowCategory", "TestTvShowRating", LocalDate.now());
        //When
        TvShow tvShow = tvShowMapper.mapToTvShow(tvShowDto);
        //Then
        assertEquals("TestTvShowTitle", tvShow.getTvShowTitle());
    }

    @Test
    public void testMapToTvShowDtoTest() {
        //Given
        TvShow tvShow = new TvShow(1L,"TestTvShowTitle", "TestTvShowCategory", "TestTvShowRating", LocalDate.now());
        //When
        TvShowDto tvShowDto = tvShowMapper.mapToTvShowDto(tvShow);
        //Then
        assertEquals("TestTvShowTitle", tvShowDto.getTvShowTitle());
    }

    @Test
    public void testMapToTvShowDtoListTest() {
        //Given
        TvShow tvShow = new TvShow(1L,"TestTvShowTitle", "TestTvShowCategory", "TestTvShowRating", LocalDate.now());
        TvShow tvShow2 = new TvShow(1L,"TestTvShowTitle2", "TestTvShowCategory2", "TestTvShowRating2", LocalDate.now());

        List<TvShow> tvShowList = new ArrayList<>();
        tvShowList.add(tvShow);
        tvShowList.add(tvShow2);
        //When
        List<TvShowDto> tvShowDtoList = tvShowMapper.mapToTvShowDtoList(tvShowList);
        //Then
        assertEquals(2, tvShowDtoList.size());
    }

    @Test
    public void testMapToTvShowListTest() {
        //Given
        TvShowDto tvShowDto = new TvShowDto(1L, "TestTvShowTitle", "TestTvShowCategory", "TestTvShowRating", LocalDate.now());
        TvShowDto tvShowDto2 = new TvShowDto(2L, "TestTvShowTitle2", "TestTvShowCategory2", "TestTvShowRating2", LocalDate.now());

        List<TvShowDto> tvShowDtoList = new ArrayList<>();
        tvShowDtoList.add(tvShowDto);
        tvShowDtoList.add(tvShowDto2);
        //When
        List<TvShow> tvShowList = tvShowMapper.mapToTvShowList(tvShowDtoList);
        //Then
        assertEquals(2, tvShowList.size());
    }
}
