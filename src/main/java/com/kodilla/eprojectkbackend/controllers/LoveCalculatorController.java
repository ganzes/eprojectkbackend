package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.clients.LoveCalculatorClient;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("eprojectk/lc")
public class LoveCalculatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveCalculatorController.class);

    @Autowired
    private LoveCalculatorClient loveCalculatorClient;

    @GetMapping
    public LoveCalculatorDto getPercentage(@RequestParam("fname") String fname, @RequestParam("sname") String sname){
        LOGGER.info("Started method getPercentage in LoveCalculatorController");
        LOGGER.info("Getting matching results for names "  + fname + " and " + sname);
        LOGGER.info("Ended method getPercentage in LoveCalculatorController");

        return loveCalculatorClient.getPercentage(fname,sname);
    }
}
