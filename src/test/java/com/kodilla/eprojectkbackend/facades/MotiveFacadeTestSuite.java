package com.kodilla.eprojectkbackend.facades;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.facade.MotivesFacade;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.services.MotiveService;
import com.kodilla.eprojectkbackend.validators.MotivesValidator;
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
    private MotivesValidator motivesValidator;
    @Mock
    private MotiveMapper motiveMapper;

    public List<Motive> getAllMotivesFacade(){
        List<MotiveDto> motiveDtoList = motiveMapper.mapToMotiveDtoList(motiveService.getAllMotive());// wykona sie w linijce 52 i 54, ewe 56, kolejnosc dziala najpierw service
        List<MotiveDto> motiveListGet = motivesValidator.validateGetAllMotives(motiveDtoList); //58 lionijcka

        return motiveMapper.mapToMotiveList(motiveListGet);
    }

    @Test
    public void getAllMotivesFacadeTest(){
//wszystkie when przed mockami
        //Given
        List<MotiveDto> motiveDtoList = new ArrayList<>();
        motiveDtoList.add(new MotiveDto(1L, "testText","testAuthor","testRating", LocalDate.now()));

        List<Motive> motiveList = new ArrayList<>();
        motiveList.add(new Motive(1L, "testText","testAuthor","testRating", LocalDate.now()));

        when(motiveService.getAllMotive()).thenReturn(motiveList);

        when(motiveMapper.mapToMotiveDtoList(motiveList)).thenReturn(motiveDtoList);

        List<MotiveDto> motiveDtoListMapped = motiveMapper.mapToMotiveDtoList(motiveList);

        when(motivesValidator.validateGetAllMotives(motiveDtoListMapped)).thenReturn(motiveDtoListMapped);
        when(motiveMapper.mapToMotiveList(motiveDtoList)).thenReturn(motiveList);



        //when(motiveMapper.mapToMotiveDtoList(motiveList)).thenReturn(motiveDtoListMapped);

        //List<Motive> motiveListMapped = motiveMapper.mapToMotiveList(motiveDtoList);


        //When
        List<Motive> motiveListFacade = motivesFacade.getAllMotivesFacade();

        assertNotNull(motiveListFacade);
        assertEquals(1, motiveListFacade.size());

    }
}
