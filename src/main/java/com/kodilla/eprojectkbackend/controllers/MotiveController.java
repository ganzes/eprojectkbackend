package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.MotvieDto;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.services.MotiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/motive")
public class MotiveController {

    @Autowired
    private MotiveService motiveService;

    @Autowired
    private MotiveMapper motiveMapper;


    @PostMapping(value = "/createMotive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMotive(@RequestBody MotvieDto motvieDto){
        motiveService.createMotive(motiveMapper.mapToMotive(motvieDto));

    }


}
