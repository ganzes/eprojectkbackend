package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.repositories.LoveCalculatorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoveCalculatorServiceTestSuite {

    @InjectMocks
    private LoveCalculatorService loveCalculatorService;

    @Mock
    private LoveCalculatorRepository loveCalculatorRepository;


    @Test
    public void createLoveCalculator(){
        //Given
        LoveCalculator loveCalculator = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");
        //When
        when(loveCalculatorRepository.save(loveCalculator)).thenReturn((loveCalculator));
        loveCalculatorService.createLoveCalculator(loveCalculator);

        //Then
        verify(loveCalculatorRepository, times(1)).save(loveCalculator);
    }

    @Test
    public void getAllResultsTest(){
        //Given
        List<LoveCalculator> loveCalculatorList = new ArrayList<>();
        loveCalculatorList.add(new LoveCalculator ("fnameTest", "snameTest", "percentageTest", "resultTest"));
        loveCalculatorList.add(new LoveCalculator ("fnameTest", "snameTest", "percentageTest", "resultTest"));

        //When
        when(loveCalculatorService.getAllResults()).thenReturn(loveCalculatorList);

        //Then
        assertEquals(2, loveCalculatorList.size());
    }


}

