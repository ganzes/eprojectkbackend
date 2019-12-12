package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotiveEntityTestSuite {
    @Autowired
    private MotiveRepository motiveRepository;

    @Test
    public void motiveSaveTest() {
        //Given
        Motive motiveSaveTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        long motiveTestID = motiveSaveTest.getMotiveID();

        //Then
        Assert.assertNotEquals(0, motiveTestID);

        //CleanUp
        motiveRepository.deleteById(motiveTestID);
    }

    @Test
    public void motiveReadTest() throws MotiveNotFoundException {
        //Given
        Motive motiveSaveTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        long motiveTestID = motiveSaveTest.getMotiveID();

        //Then
        Motive resultReadTest = motiveRepository.findById(motiveTestID).orElseThrow(MotiveNotFoundException::new);

        Assert.assertEquals("testMotiveText", resultReadTest.getMotiveText());
        Assert.assertEquals("testMotiveAuthor", resultReadTest.getMotiveAuthor());

        //CleanUp
        motiveRepository.deleteById(motiveTestID);
    }

    @Test
    public void motiveUpdateTest() throws MotiveNotFoundException {
        //Given
        Motive motiveSaveTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

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
    }

    @Test
    public void motiveDeleteTest() throws MotiveNotFoundException{
        //Given
        Motive motiveSaveTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        long motiveTestID = motiveSaveTest.getMotiveID();
        long countBeforeDelete = motiveRepository.count();

        //Then
        motiveRepository.deleteById(motiveTestID);
        long countAfterDelete = motiveRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }

    @Test
    public void deleteAllMotives() {
        //Given
        Motive motiveSaveTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());
        Motive motiveSaveTest2 = new Motive(2L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveSaveTest);
        motiveRepository.save(motiveSaveTest2);
        long motiveTestID = motiveSaveTest.getMotiveID();
        long motiveTestID2 = motiveSaveTest2.getMotiveID();
        long countBeforeDelete = motiveRepository.count();

        //Then
        motiveRepository.deleteAll();
        long countAfterDelete = motiveRepository.count();

        Assert.assertEquals(countBeforeDelete - 2, countAfterDelete);
    }

    @Test
    public void countAllMotives() {
        //Given
        Motive motiveCountTest = new Motive(1L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());
        Motive motiveCountTest2 = new Motive(2L,"testMotiveText", "testMotiveAuthor", "9", LocalDate.now());

        //When
        motiveRepository.save(motiveCountTest2);
        long countMotiveCountTest = motiveRepository.count();

        motiveRepository.save(motiveCountTest);
        long countMotiveCountTest2 = motiveRepository.count();
        //Then

        Assert.assertEquals(1, countMotiveCountTest);
        Assert.assertEquals(2, countMotiveCountTest2);
    }
}