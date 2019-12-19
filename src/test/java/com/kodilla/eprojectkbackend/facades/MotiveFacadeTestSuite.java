package com.kodilla.eprojectkbackend.facades;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.facade.MotivesFacade;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.services.MotiveService;
import com.kodilla.eprojectkbackend.validators.MotiveValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MotiveFacadeTestSuite {
    @InjectMocks
    private MotivesFacade motivesFacade;
    @Mock
    private MotiveService motiveService;
    @Mock
    private MotiveValidator motiveValidator;
    @Mock
    private MotiveMapper motiveMapper;

    @Test
    public void getAllMotivesFacadeTest(){
        //Given
        List<MotiveDto> motiveDtoList = new ArrayList<>();
        motiveDtoList.add(new MotiveDto(1L, "testText","testAuthor","testRating", LocalDate.now()));

        List<Motive> motiveList = new ArrayList<>();
        motiveList.add(new Motive(1L, "testText","testAuthor","testRating", LocalDate.now()));

        when(motiveService.getAllMotive()).thenReturn(motiveList);
        when(motiveMapper.mapToMotiveDtoList(motiveList)).thenReturn(motiveDtoList);
        when(motiveMapper.mapToMotiveList(motiveDtoList)).thenReturn(motiveList);

        //When
        List<Motive> motiveListFacade = motivesFacade.getAllMotivesFacade();

        assertNotNull(motiveListFacade);
        assertEquals(1, motiveListFacade.size());

        motiveListFacade.forEach(motive -> {
            assertEquals(1L, motiveList.size());
        });

    }
}
