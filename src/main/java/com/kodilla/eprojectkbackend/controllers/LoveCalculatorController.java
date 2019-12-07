package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.client.LoveCalculatorClient;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("eprojectk/lc")
public class LoveCalculatorController {

    @Autowired
    private LoveCalculatorClient loveCalculatorClient;

    @GetMapping
    public LoveCalculatorDto getPercentage(@RequestParam("fname") String fname, @RequestParam("sname") String sname){
        return loveCalculatorClient.getPercentage(fname,sname);
    }
}
