package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.clients.LoveCalculatorClient;
import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import com.kodilla.eprojectkbackend.mappers.LoveCalculatorMapper;
import com.kodilla.eprojectkbackend.services.LoveCalculatorService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("eprojectk/lc")
public class LoveCalculatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveCalculatorController.class);

    @Autowired
    private LoveCalculatorClient loveCalculatorClient;

    @Autowired
    private LoveCalculatorMapper loveCalculatorMapper;

    @Autowired
    private LoveCalculatorService loveCalculatorService;

    @GetMapping
    public LoveCalculatorDto getPercentage(@RequestParam("fname") String fname, @RequestParam("sname") String sname){
        LOGGER.info("Started method getPercentage in LoveCalculatorController.");
        LOGGER.info("Getting matching results for names "  + fname + " and " + sname +".");
        LOGGER.info("Ended method getPercentage in LoveCalculatorController.");

        return loveCalculatorClient.getPercentage(fname,sname);
    }

    @GetMapping(value = "/getResults")
    public List<LoveCalculatorDto> getResults() {
        LOGGER.info("Started method getResults in LoveCalculatorController.");
        LOGGER.info("Ended method getResults in LoveCalculatorController.");

        return loveCalculatorMapper.mapToLoveCalculatorDtoList(loveCalculatorService.getAllResults());
    }

    //added for further implementation
/*    @GetMapping("/export-LoveCalculatorResults")
    public void exportCSV(HttpServletResponse response) throws Exception {
        //set file name and content type
        String filename = "Love Calculator Results.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<LoveCalculator> writer = new StatefulBeanToCsvBuilder<LoveCalculator>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(loveCalculatorService.getAllResults());
    }*/
}
