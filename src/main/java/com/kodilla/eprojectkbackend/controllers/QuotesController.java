package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.clients.QuotesClient;
import com.kodilla.eprojectkbackend.domains.QuotesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@CrossOrigin("*")
@RequestMapping("eprojectk/quotes")
public class QuotesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesController.class);

    @Autowired
    private QuotesClient quotesClient;

    @GetMapping(value = "/getRandom")
    public QuotesDto getRandomQuote() throws HttpServerErrorException {
        LOGGER.info("Started method getRandomQuote in QuotesController.");
        LOGGER.info("Ended method getRandomQuote in QuotesController.");

        return quotesClient.getRandomQuoteClient();
    }

    @GetMapping
    public QuotesDto getQuoteByKeyword(@RequestParam("keyword") String keyword) {
        LOGGER.info("Started method getQuoteByKeyword in QuotesController.");

        if (keyword.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, keyword + "Keyword is empty!");
        }
        LOGGER.info("Ended method getQuoteByKeyword in QuotesController, " + keyword + ".");

        return quotesClient.getQuoteByKeywordClient(keyword);
    }

    @GetMapping(value = "/byAuthor")
    public QuotesDto getQuoteByAuthor(@RequestParam("author") String author) {
        LOGGER.info("Started method getQuoteByAuthor in QuotesController.");

        if (author.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, author + "Author is empty!");
        }

        LOGGER.info("Ended method getQuoteByAuthor in QuotesController, " + author + ".");

        return quotesClient.getQuoteByAuthorClient(author);
    }
}
