package com.kodilla.eprojectkbackend.validators;

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
public class MotiveValidatorTestSuite {

    @Autowired
    private MotiveValidator motiveValidator;

    @Test
    public void validateGetAllMotivesTest() {
        //Given
        List<MotiveDto> motiveDtoList = new ArrayList<>();
        motiveDtoList.add(new MotiveDto(1L, "testText", "testAuthorValidator", "testRating", LocalDate.now()));

        //When
        List<MotiveDto> validatedMotiveDtoList = motiveValidator.validateGetAllMotives(motiveDtoList);

        //Then
        assertEquals(1, validatedMotiveDtoList.size());
    }
}