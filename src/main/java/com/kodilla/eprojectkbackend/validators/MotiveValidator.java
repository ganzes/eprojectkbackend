package com.kodilla.eprojectkbackend.validators;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MotiveValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MotiveValidator.class);

    public List<MotiveDto> validateGetAllMotives (final List<MotiveDto> motiveList){
        LOGGER.info("Method started validateGetAllMotives in MotivesValidator");
        List<MotiveDto> filtered = motiveList.stream()
                .filter(motive -> !motive.getMotiveAuthor().equalsIgnoreCase("testAuthor"))
                .collect(Collectors.toList());
        LOGGER.info("Method ended validateGetAllMotives in MotivesValidator, Motives size: " + filtered.size());
        return filtered;
    }
}
