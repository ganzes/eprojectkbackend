package com.kodilla.eprojectkbackend.scheduleds;

import com.kodilla.eprojectkbackend.clients.LoveCalculatorClient;
import com.kodilla.eprojectkbackend.repositories.LoveCalculatorRepository;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Calendar;

@EnableScheduling
@Component
public class ScheduledInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveCalculatorClient.class);

    @Autowired
    private MotiveRepository motiveRepository;

    @Autowired
    private LoveCalculatorRepository loveCalculatorRepository;

    //@Scheduled(fixedRate = 5000)
    public void checkMotiveDBSize() {
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
    }

    //@Scheduled(fixedRate = 5000)
    public void checkLoveCalculatorDBSize() {
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
    }
}
