package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.facade.MotivesFacade;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import com.kodilla.eprojectkbackend.services.MotiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/motive")
public class MotiveController {

    @Autowired
    private MotiveService motiveService;

    @Autowired
    private MotiveMapper motiveMapper;

    @Autowired
    private MotiveRepository motiveRepository;

    @Autowired
    private MotivesFacade motivesFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(MotiveController.class);

    @PostMapping(value = "/createMotive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMotive(@RequestBody MotiveDto motiveDto) {
        LOGGER.info("Started method createMotive in MotiveController.");

        motiveService.createMotive(motiveMapper.mapToMotive(motiveDto));

        LOGGER.info("Ended method createMotive in MotiveController.");
    }

    @GetMapping(value = "/getMotive")
    public MotiveDto getMotive(@RequestParam Long motiveID) throws MotiveNotFoundException {
        LOGGER.info("Started method getMotive in MotiveController.");
        return motiveMapper.mapToMotiveDto(motiveService.findMotiveByID(motiveID));
    }

    @GetMapping(value = "/getMotives")
    public List<MotiveDto> getMotives() {
        LOGGER.info("Started method getMotives in MotiveController.");
        LOGGER.info("Ended method getMotive in MotiveController.");

        return motiveMapper.mapToMotiveDtoList(motiveService.getAllMotive());
    }

    /*@PutMapping(value = "/updateMotive")
    public MotiveDto updateMotive(@RequestBody MotiveDto motiveDto) throws MotiveNotFoundException{

        System.out.println("TTUUUTAJ!!! " + motiveDto.getMotiveID());
        Motive updateMotive = motiveService.updateMotive(motiveMapper.mapToMotive(motiveDto));

        return motiveMapper.mapToMotiveDto(updateMotive);
    }*/

    @PutMapping(value = "/updateMotive")
    public MotiveDto updateMotive(@RequestBody MotiveDto motiveDto) throws MotiveNotFoundException {
        LOGGER.info("Started method updateMotive in MotiveController.");

        Motive motive = motiveRepository.findById(motiveDto.getMotiveID()).orElseThrow(MotiveNotFoundException::new);
        motive.setMotiveText(motiveDto.getMotiveText());
        motive.setMotiveAuthor(motiveDto.getMotiveAuthor());
        motive.setMotiveRating(motiveDto.getMotiveRating());
        Motive updateMotive = motiveService.updateMotive(motive);

        LOGGER.info("Ended method deleteMotive in MotiveController.");

        return motiveMapper.mapToMotiveDto(updateMotive);
    }

    @DeleteMapping(value = "/deleteMotive")
    public void deleteMotive(@RequestParam Long motiveID) throws MotiveNotFoundException {
        LOGGER.info("Started method deleteMotive in MotiveController.");

        motiveService.deleteMotiveByID(motiveID);

        LOGGER.info("Ended method deleteMotive in MotiveController.");
    }

    @DeleteMapping(value = "/deleteAllMotives")
    public void deleteAllMotives() {
        LOGGER.info("Started method deleteAllMotives in MotiveController.");

        motiveService.deleteAllMotives();

        LOGGER.info("Ended method deleteAllMotives in MotiveController.");
    }

    @GetMapping(value = "/getMotiveByAuthor")
    public List<MotiveDto>  getMotiveByAuthor(@RequestParam String motiveAuthor) {
        LOGGER.info("Started method getMotiveByAuthor in MotiveController.");

        return motiveMapper.mapToMotiveDtoList(motiveService.findMotiveByAuthor(motiveAuthor));
    }

    @GetMapping(value = "/getMotiveByRating")
    public List<MotiveDto>  getMotiveByRating(@RequestParam String motiveRating) {
        LOGGER.info("Started method getMotiveByRating in MotiveController.");

        return motiveMapper.mapToMotiveDtoList(motiveService.findMotiveByRating(motiveRating));
    }

    @GetMapping(value = "/countAllMotives")
    public Long countAllMotives() {
        LOGGER.info("Started method countAllMotives in MotiveController.");
        long allMotives = motiveService.countAllMotives();

        LOGGER.info("Ended method countAllMotives in MotiveController.");
        return allMotives;
    }

    @GetMapping(value = "/getMotivesFacade")
    public List<Motive> getMotivesFacade() {
        LOGGER.info("Started method getMotives in MotiveController.");
        LOGGER.info("Ended method getMotive in MotiveController.");

        return motivesFacade.getAllMotivesFacade();
    }
}
