package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import com.kodilla.eprojectkbackend.services.MotiveService;
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

    @PostMapping(value = "/createMotive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMotive(@RequestBody MotiveDto motiveDto) {
        motiveService.createMotive(motiveMapper.mapToMotive(motiveDto));
    }

    @GetMapping(value = "/getMotive")
    public MotiveDto getMotive(@RequestParam Long motiveID) throws MotiveNotFoundException {
        return motiveMapper.mapToMotiveDto(motiveService.findMotiveByID(motiveID));
    }

    @GetMapping(value = "/getMotives")
    public List<MotiveDto> getMotives() {
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
        Motive motive = motiveRepository.findById(motiveDto.getMotiveID()).orElseThrow(MotiveNotFoundException::new);
        motive.setMotiveText(motiveDto.getMotiveText());
        motive.setMotiveAuthor(motiveDto.getMotiveAuthor());
        motive.setMotiveRating(motiveDto.getMotiveRating());
        Motive updateMotive = motiveService.updateMotive(motive);
        return motiveMapper.mapToMotiveDto(updateMotive);
    }

    @DeleteMapping(value = "/deleteMotive")
    public void deleteMotive(@RequestParam Long motiveID) throws MotiveNotFoundException {
        motiveService.deleteMotiveByID(motiveID);
    }


}
