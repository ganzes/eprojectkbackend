package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoveCalculatorMapperTestSuite {

    @Autowired
    private LoveCalculatorMapper loveCalculatorMapper;

    @Test
    public void testMapToLoveCalculatorTest() {
        //Given
        LoveCalculatorDto loveCalculatorDto = new LoveCalculatorDto("fnameTest", "snameTest", "percentageTest", "resultTest");
        //When
        LoveCalculator loveCalculator = loveCalculatorMapper.mapToLoveCalculator(loveCalculatorDto);
        //Then
        assertEquals("percentageTest", loveCalculator.getPercentage());
    }

    @Test
    public void testMapToLoveCalculatorDtoTest() {
        //Given
        LoveCalculator loveCalculator = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");
        //When
        LoveCalculatorDto loveCalculatorDto = loveCalculatorMapper.mapToLoveCalculatorDto(loveCalculator);
        //Then
        assertEquals("percentageTest", loveCalculatorDto.getPercentage());
    }

    @Test
    public void testMapToLoveCalculatorDtoListTest() {
        //Given
        LoveCalculator loveCalculator = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");
        LoveCalculator loveCalculator2 = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        List<LoveCalculator> loveCalculatorList = new ArrayList<>();
        loveCalculatorList.add(loveCalculator);
        loveCalculatorList.add(loveCalculator2);
        //When
        List<LoveCalculatorDto> loveCalculatorDtoList = loveCalculatorMapper.mapToLoveCalculatorDtoList(loveCalculatorList);
        //Then
        assertEquals(2, loveCalculatorDtoList.size());
    }

    @Test
    public void testMapToLoveCalculatorListTest() {
        //Given
        LoveCalculatorDto loveCalculatorDto = new LoveCalculatorDto("fnameTest", "snameTest", "percentageTest", "resultTest");
        LoveCalculatorDto loveCalculatorDto2 = new LoveCalculatorDto("fnameTest", "snameTest", "percentageTest", "resultTest");

        List<LoveCalculatorDto> loveCalculatorDtoList = new ArrayList<>();
        loveCalculatorDtoList.add(loveCalculatorDto);
        loveCalculatorDtoList.add(loveCalculatorDto2);
        //When
        List<LoveCalculator> loveCalculatorList = loveCalculatorMapper.mapToLoveCalculatorList(loveCalculatorDtoList);
        //Then
        assertEquals(2, loveCalculatorList.size());
    }
}