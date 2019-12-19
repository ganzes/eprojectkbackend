package com.kodilla.eprojectkbackend.scheduleds;

import com.kodilla.eprojectkbackend.clients.LoveCalculatorClient;
import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.repositories.LoveCalculatorRepository;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledInfoTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveCalculatorClient.class);

    @Mock
    private ScheduledInfo scheduledInfo;

    @Mock
    private LoveCalculatorRepository loveCalculatorRepository;

    @Mock
    private MotiveRepository motiveRepository;

    @Test
    public void checkMotiveDBSizeTest() {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        Motive savedMotive = motiveRepository.save(motiveSaveTest);

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int seconds = rightNow.get(Calendar.SECOND);

        long motivesCountDB = motiveRepository.count();

        String entryOrEntries = (motivesCountDB <= 1) ? " entry" : " entries";

        if (motivesCountDB != 0) {
            LOGGER.info("Currently they are " + motivesCountDB + entryOrEntries + " in MotivesDB |" +
                    " Date: " + LocalDate.now()
                    + " | Time: " + hour + ":" + minutes + ":" + seconds);
        } else {
            LOGGER.info("MotivesDB is empty!");
        }

        //When
        doNothing().doThrow(new RuntimeException()).when(scheduledInfo).checkMotiveDBSize();
        scheduledInfo.checkMotiveDBSize();

        //Then
        verify(scheduledInfo, times(1)).checkMotiveDBSize();
    }

    @Test
    public void checkLoveCalculatorDBSizeTest() {
        //Given
        LoveCalculator loveCalculatorTest = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        LoveCalculator savedLoveCalculatorTest = loveCalculatorRepository.save(loveCalculatorTest);

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int seconds = rightNow.get(Calendar.SECOND);

        long loveCalculatorCountDB = loveCalculatorRepository.count();

        String entryOrEntries = (loveCalculatorCountDB <= 1) ? " entry" : " entries";

        if (loveCalculatorCountDB != 0) {
            LOGGER.info("Currently they are " + loveCalculatorCountDB + entryOrEntries + " in LoveCalculatorDB |" +
                    " Date: " + LocalDate.now()
                    + " | Time: " + hour + ":" + minutes + ":" + seconds);
        } else {
            LOGGER.info("LoveCalculatorDB is empty!");
        }

        //When
        doNothing().doThrow(new RuntimeException()).when(scheduledInfo).checkMotiveDBSize();
        scheduledInfo.checkMotiveDBSize();

        //Then
        verify(scheduledInfo, times(1)).checkMotiveDBSize();
    }
}