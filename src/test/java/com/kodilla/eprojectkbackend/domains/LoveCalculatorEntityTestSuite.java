package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.LoveCalculatorNotFoundException;
import com.kodilla.eprojectkbackend.repositories.LoveCalculatorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoveCalculatorEntityTestSuite {

    @Autowired
    private LoveCalculatorRepository loveCalculatorRepository;

    @Test
    public void LoveCalculatorSaveTest() {
        //Given
        LoveCalculator loveCalculatorTest = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        //When
        LoveCalculator savedLoveCalculator = loveCalculatorRepository.save(loveCalculatorTest);
        long loveCalculatorTestID = savedLoveCalculator.getLoveCalculatorID();

        //Then
        Assert.assertNotEquals(0, loveCalculatorTestID);
        Assert.assertEquals(1L, loveCalculatorTestID);

        //CleanUp
        loveCalculatorRepository.deleteById(loveCalculatorTestID);
        loveCalculatorRepository.deleteAll();
    }

    @Test
    public void loveCalculatorReadTest() throws LoveCalculatorNotFoundException {
        //Given
        LoveCalculator loveCalculatorTest = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        //When
        loveCalculatorRepository.save(loveCalculatorTest);
        long loveCalculatorTestID = loveCalculatorTest.getLoveCalculatorID();

        //Then
        LoveCalculator resultReadTest = loveCalculatorRepository.findById(loveCalculatorTestID).orElseThrow(LoveCalculatorNotFoundException::new);

        Assert.assertEquals("percentageTest", resultReadTest.getPercentage());
        Assert.assertEquals("resultTest", resultReadTest.getResult());

        //CleanUp
        loveCalculatorRepository.deleteById(loveCalculatorTestID);
        loveCalculatorRepository.deleteAll();
    }

    @Test
    public void loveCalculatorUpdateTest() throws LoveCalculatorNotFoundException {
        //Given
        LoveCalculator loveCalculatorTest = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        //When
        loveCalculatorRepository.save(loveCalculatorTest);
        long loveCalculatorTestID = loveCalculatorTest.getLoveCalculatorID();

        //Then
        LoveCalculator updateTest = loveCalculatorRepository.findById(loveCalculatorTestID).orElseThrow(LoveCalculatorNotFoundException::new);
        updateTest.setResult("testLoveCalculatorTextUpdated");
        loveCalculatorRepository.save(updateTest);

        LoveCalculator resultUpdateTest = loveCalculatorRepository.findById(loveCalculatorTestID).orElseThrow(LoveCalculatorNotFoundException::new);

        //Then
        Assert.assertEquals("testLoveCalculatorTextUpdated", resultUpdateTest.getResult());

        //CleanUp
        loveCalculatorRepository.deleteById(loveCalculatorTestID);
        loveCalculatorRepository.deleteAll();
    }

    @Test
    public void loveCalculatorDeleteTest() {
        //Given
        LoveCalculator loveCalculatorTest = new LoveCalculator("fnameTest", "snameTest", "percentageTest", "resultTest");

        //When
        loveCalculatorRepository.save(loveCalculatorTest);
        long loveCalculatorTestID = loveCalculatorTest.getLoveCalculatorID();
        long countBeforeDelete = loveCalculatorRepository.count();

        //Then
        loveCalculatorRepository.deleteById(loveCalculatorTestID);
        long countAfterDelete = loveCalculatorRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        loveCalculatorRepository.deleteAll();
    }
}