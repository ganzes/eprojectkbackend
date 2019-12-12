package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
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
public class MotiveMapperTestSuite {

    @Autowired
    private MotiveMapper motiveMapper;

    @Test
    public void testMapToMotive() {
        //Given
        MotiveDto motiveDto = new MotiveDto(1L, "TestMotiveText", "TestMotiveAuthor", "TestMotiveRating", LocalDate.now());
        //When
        Motive motive = motiveMapper.mapToMotive(motiveDto);
        //Then
        assertEquals("TestMotiveText", motive.getMotiveText());
    }

    @Test
    public void testMapToMotiveDto() {
        //Given
        Motive motive = new Motive(1L,"TestMotiveText", "TestMotiveAuthor", "TestMotiveRating", LocalDate.now());
        //When
        MotiveDto motiveDto = motiveMapper.mapToMotiveDto(motive);
        //Then
        assertEquals("TestMotiveText", motiveDto.getMotiveText());
    }

    @Test
    public void testMapToMotiveDtoList() {
        //Given
        Motive motive = new Motive(1L,"TestMotiveText", "TestMotiveAuthor", "TestMotiveRating", LocalDate.now());
        Motive motive2 = new Motive(1L,"TestMotiveText2", "TestMotiveAuthor2", "TestMotiveRating2", LocalDate.now());

        List<Motive> motiveList = new ArrayList<>();
        motiveList.add(motive);
        motiveList.add(motive2);
        //When
        List<MotiveDto> motiveDtoList = motiveMapper.mapToMotiveDtoList(motiveList);
        //Then
        assertEquals(2, motiveDtoList.size());
    }

    @Test
    public void testMapToMotiveList() {
        //Given
        MotiveDto motiveDto = new MotiveDto(1L, "TestMotiveText", "TestMotiveAuthor", "TestMotiveRating", LocalDate.now());
        MotiveDto motiveDto2 = new MotiveDto(2L, "TestMotiveText2", "TestMotiveAuthor2", "TestMotiveRating2", LocalDate.now());

        List<MotiveDto> motiveDtoList = new ArrayList<>();
        motiveDtoList.add(motiveDto);
        motiveDtoList.add(motiveDto2);
        //When
        List<Motive> motiveList = motiveMapper.mapToMotiveList(motiveDtoList);
        //Then
        assertEquals(2, motiveList.size());
    }
}
