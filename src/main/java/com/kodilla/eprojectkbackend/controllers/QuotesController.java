package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.clients.QuotesClient;
import com.kodilla.eprojectkbackend.domains.QuotesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("eprojectk/quotes")
public class QuotesController {

    @Autowired
    private QuotesClient quotesClient;

    @GetMapping(value = "/getRandom")
    public QuotesDto getRandomQuote(){
        return quotesClient.getRandomQuoteClient();
    }
}
