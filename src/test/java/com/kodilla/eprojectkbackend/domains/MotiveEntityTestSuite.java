package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MotiveEntityTestSuite {
    @Autowired
    private MotiveRepository motiveRepository;

    @Test
    public void motiveSaveTest() {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        Motive savedMotive = motiveRepository.save(motiveSaveTest);
        long motiveTestID = savedMotive.getMotiveID();

        //Then
        Assert.assertNotEquals(0, motiveTestID);
        Assert.assertEquals(1L, motiveTestID);

        //CleanUp
        motiveRepository.deleteById(motiveTestID);
        motiveRepository.deleteAll();
    }

    @Test
    public void motiveReadTest() throws MotiveNotFoundException {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        Motive savedMotive = motiveRepository.save(motiveSaveTest);
        long motiveTestID = savedMotive.getMotiveID();

        //Then
        Motive resultReadTest = motiveRepository.findById(motiveTestID).orElseThrow(MotiveNotFoundException::new);

        Assert.assertEquals("testMotiveText", resultReadTest.getMotiveText());
        Assert.assertEquals("testMotiveAuthor", resultReadTest.getMotiveAuthor());

        //CleanUp
        motiveRepository.deleteById(motiveTestID);
        motiveRepository.deleteAll();
    }

    @Test
    public void motiveUpdateTest() throws MotiveNotFoundException {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        long motiveTestID = motiveSaveTest.getMotiveID();

        //Then
        Motive updateTest = motiveRepository.findById(motiveTestID).orElseThrow(MotiveNotFoundException::new);
        updateTest.setMotiveText("testMotiveTextUpdated");
        motiveRepository.save(updateTest);

        Motive resultUpdateTest = motiveRepository.findById(motiveTestID).orElseThrow(MotiveNotFoundException::new);

        //Then
        Assert.assertEquals("testMotiveTextUpdated", resultUpdateTest.getMotiveText());

        //CleanUp
        motiveRepository.deleteById(motiveTestID);
        motiveRepository.deleteAll();
    }

    @Test
    public void motiveDeleteTest() throws MotiveNotFoundException {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        long motiveTestID = motiveSaveTest.getMotiveID();
        long countBeforeDelete = motiveRepository.count();

        //Then
        motiveRepository.deleteById(motiveTestID);
        long countAfterDelete = motiveRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);

        //Clean up
        motiveRepository.deleteAll();
    }

    @Test
    public void deleteAllMotives() {
        //Given
        Motive motiveSaveTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());
        Motive motiveSaveTest2 = new Motive(2L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        motiveRepository.save(motiveSaveTest2);

        long countBeforeDelete = motiveRepository.count();

        //Then
        motiveRepository.deleteAll();
        long countAfterDelete = motiveRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);

        //Clean up
        motiveRepository.deleteAll();
    }

    @Test
    public void countAllMotives() {
        //Given
        Motive motiveCountTest = new Motive(1L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());
        Motive motiveCountTest2 = new Motive(2L, "testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveCountTest);
        long countMotiveCountTest = motiveRepository.count();

        motiveRepository.save(motiveCountTest2);
        long countMotiveCountTest2 = motiveRepository.count();
        //Then

        Assert.assertEquals(1, countMotiveCountTest);
        Assert.assertEquals(2, countMotiveCountTest2);

        //Clean up
        motiveRepository.deleteAll();
    }
}