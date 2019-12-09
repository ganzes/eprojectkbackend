package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.clients.QuotesClient;
import com.kodilla.eprojectkbackend.domains.QuotesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public QuotesDto getQuoteByKeyword(@RequestParam ("keyword") String keyword){
        return quotesClient.getQuoteByKeywordClient(keyword);
    }
}
