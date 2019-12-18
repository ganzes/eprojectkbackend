package com.kodilla.eprojectkbackend.clients;

import com.kodilla.eprojectkbackend.configuration.LoveCalculatorConfiguration;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import com.kodilla.eprojectkbackend.mappers.LoveCalculatorMapper;
import com.kodilla.eprojectkbackend.services.LoveCalculatorService;
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
public class LoveCalculatorClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoveCalculatorClient.class);

    private static final LoveCalculatorDto errorMessageGetPercentageClient =
            new LoveCalculatorDto("Please try again | ",
                    "Check you spelling | ", "Example: John, Kate | ", "Or server is busy, try again!");

    private static final LoveCalculatorDto errorMessageGetPercentageClient2 =
            new LoveCalculatorDto(" not found ",
                    " not found ", " 0 ", "Please enter correct format name!");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoveCalculatorConfiguration loveCalculatorConfiguration;

    @Autowired
    private LoveCalculatorService loveCalculatorService;

    @Autowired
    private LoveCalculatorMapper loveCalculatorMapper;

    public LoveCalculatorDto getPercentage(String fname, String sname) {
        try {
            LOGGER.info("Starting method getPercentage in LoveCalculatorClient");
            LOGGER.info("Getting matching results for names " + fname + " and " + sname);

            if (fname == null || sname == null) {
                if (fname == null) {
                    LOGGER.error("fname name is null! " + fname);
                }
                if (sname == null) {
                    LOGGER.error("sname is null!" + sname);
                }
                return errorMessageGetPercentageClient2;
            }

            URI url = UriComponentsBuilder.fromHttpUrl("https://love-calculator.p.rapidapi.com/getPercentage")
                    .queryParam("fname", fname)
                    .queryParam("sname", sname)
                    .build().encode().toUri();
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("X-RapidAPI-Host", loveCalculatorConfiguration.getLoveCalculatorEndpoint());
            headers.add("X-RapidAPI-Key", loveCalculatorConfiguration.getLoveCalculatorKey());

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<LoveCalculatorDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, LoveCalculatorDto.class);

            LOGGER.info("Ended getPercentage in LoveCalculatorClient.");

            loveCalculatorService.createLoveCalculator(loveCalculatorMapper.mapToLoveCalculator(response.getBody()));

            return response.getBody();

        } catch (HttpServerErrorException e) {
            LOGGER.error("HttpServerErrorException " + e);
        }

        LOGGER.warn("Ended method getRandomQuoteClient in QuotesClient = failure.");

        return errorMessageGetPercentageClient;
    }
}