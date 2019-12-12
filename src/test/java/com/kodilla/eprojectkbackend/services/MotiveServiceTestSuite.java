package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
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
public class MotiveServiceTestSuite {

    @InjectMocks
    private MotiveService motiveService;

    @Mock
    private MotiveRepository motiveRepository;

    @Test
    public void createMotiveTest(){
        //Given
        Motive motiveTest = new Motive("testMotiveText","testMotiveAuthor", "testMotiveRating", LocalDate.now());

        //When
        when(motiveRepository.save(motiveTest)).thenReturn((motiveTest));
        motiveService.createMotive(motiveTest);

        //Then
        verify(motiveRepository, times(1)).save(motiveTest);
    }

    @Test
    public void getAllMotiveTest(){
        //Given
        List<Motive> motiveListTest = new ArrayList<>();
        motiveListTest.add(new Motive("testMotiveText","testMotiveAuthor", "testMotiveRating", LocalDate.now()));
        motiveListTest.add(new Motive("testMotiveText2","testMotiveAuthor2", "testMotiveRating2", LocalDate.now()));

        //When
        when(motiveService.getAllMotive()).thenReturn(motiveListTest);

        //Then
        assertEquals(2, motiveListTest.size());
    }

    @Test
    public void findMotiveByAuthorTest(){
        //Given
        List<Motive> motiveListTest = new ArrayList<>();
        Motive motiveTest = new Motive("testMotiveText","testMotiveAuthor", "testMotiveRating", LocalDate.now());
        motiveListTest.add(motiveTest);

        String motiveTestAuthor = motiveTest.getMotiveAuthor();
        String motiveTestText = motiveTest.getMotiveText();

        //When
        when(motiveRepository.findByMotiveAuthor(motiveTestAuthor)).thenReturn(motiveListTest);

        //Then
        assertEquals("testMotiveText", motiveTestText);
        assertEquals("testMotiveAuthor", motiveTestAuthor);
    }

    @Test
    public void findMotiveByIDTest(){
        //Given
        Motive motiveTest = new Motive("testMotiveText","testMotiveAuthor", "testMotiveRating", LocalDate.now());
        Long motiveTestID = motiveTest.getMotiveID();
        Optional<Motive> optionalMotiveTest = Optional.of(motiveTest);
        String motiveTestText = motiveTest.getMotiveText();
        //When
        when(motiveRepository.findById(motiveTestID)).thenReturn(optionalMotiveTest);

        //Then
        assertEquals("testMotiveText", motiveTestText);
    }
}