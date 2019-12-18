package com.kodilla.eprojectkbackend.clients;

import com.kodilla.eprojectkbackend.configuration.QuotesConfiguration;
import com.kodilla.eprojectkbackend.domains.QuotesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class QuotesClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesClient.class);

    private static final QuotesDto errorMessageGetQuoteByKeywordClient =
            new QuotesDto("Please try again\n", "Check you spelling", "Example: Wisdom, Life", "", "", "", "");

    private static final QuotesDto errorMessageGetRandomQuoteClient =
            new QuotesDto("Please try again\n", "", "", "", "", "", "");

    private static final QuotesDto errorMessageGetQuoteByAuthorClient =
            new QuotesDto("Please try again\n", "Example: John, Adam", "", "", "", "", "");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QuotesConfiguration quotesConfiguration;

    public QuotesDto getRandomQuoteClient() {

        try {
            LOGGER.info("Starting method getRandomQuoteClient in QuotesClient.");

            URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/random")
                    .build().encode().toUri();

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
            headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

            LOGGER.info("Ended method getRandomQuoteClient in QuotesClient = success.");

            return response.getBody();

        } catch (HttpServerErrorException e) {
            LOGGER.error("HttpServerErrorException " + e);
        }

        LOGGER.warn("Ended method getRandomQuoteClient in QuotesClient = failure.");

        return errorMessageGetRandomQuoteClient;
    }

    public QuotesDto getQuoteByKeywordClient(String keyword) throws HttpServerErrorException {

        try {
            LOGGER.info("Starting method getQuoteByKeywordClient in QuotesClient");
            LOGGER.info("Keyword " + keyword);

            if (keyword != null) {
                URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/keyword/" + keyword)
                        .build().encode().toUri();

                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
                headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

                HttpEntity<?> entity = new HttpEntity<>(headers);

                HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

                LOGGER.info("Ended method getQuoteByKeywordClient in QuotesClient = success.");

                return response.getBody();

            } else {
                LOGGER.error("Keyword is null! " + keyword);
            }

        } catch (HttpServerErrorException e) {
            LOGGER.error("HttpServerErrorException " + e);
        }

        LOGGER.warn("Ended method getQuoteByKeywordClient in QuotesClient = failure.");

        return errorMessageGetQuoteByKeywordClient;
    }

    public QuotesDto getQuoteByAuthorClient(String author) throws HttpServerErrorException {

        try {
            LOGGER.info("Starting method getQuoteByAuthorClient in QuotesClient.");
            LOGGER.info("Author " + author);

            if (author == null) {
                LOGGER.error("Author was null! " + author);
            }

            URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/author/" + author)
                    .build().encode().toUri();

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
            headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

            LOGGER.info("Ended method getQuoteByAuthorClient in QuotesClient = success.");

            return response.getBody();

        } catch (HttpServerErrorException e) {
            LOGGER.error("HttpServerErrorException " + e);
        }

        LOGGER.warn("Ended method getQuoteByAuthorClient in QuotesClient = failure.");

        return errorMessageGetQuoteByAuthorClient;
    }
}