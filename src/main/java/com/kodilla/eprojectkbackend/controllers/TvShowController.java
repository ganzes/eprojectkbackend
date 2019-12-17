package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.domains.TvShowDto;
import com.kodilla.eprojectkbackend.exceptions.TvShowNotFoundException;
import com.kodilla.eprojectkbackend.mappers.TvShowMapper;
import com.kodilla.eprojectkbackend.repositories.TvShowRepository;
import com.kodilla.eprojectkbackend.services.TvShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/tvShow")
public class TvShowController {

    @Autowired
    private TvShowService tvShowService;

    @Autowired
    private TvShowMapper tvShowMapper;

    @Autowired
    private TvShowRepository tvShowRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowController.class);

    @PostMapping(value = "/createTvShow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTvShow(@RequestBody TvShowDto tvShowDto) {
        LOGGER.info("Started method createTvShow in TvShowController.");

        tvShowService.createTvShow(tvShowMapper.mapToTvShow(tvShowDto));

        LOGGER.info("Ended method createTvShow in TvShowController.");
    }

    @GetMapping(value = "/getTvShow")
    public TvShowDto getTvShow(@RequestParam Long tvShowID) throws TvShowNotFoundException {
        LOGGER.info("Started method getTvShow in TvShowController.");
        return tvShowMapper.mapToTvShowDto(tvShowService.findTvShowByID(tvShowID));
    }

    @GetMapping(value = "/getTvShows")
    public List<TvShowDto> getTvShows() {
        LOGGER.info("Started method getTvShows in TvShowController.");
        LOGGER.info("Ended method getTvShow in TvShowController.");

        return tvShowMapper.mapToTvShowDtoList(tvShowService.getAllTvShow());
    }

    @PutMapping(value = "/updateTvShow")
    public TvShowDto updateTvShow(@RequestBody TvShowDto tvShowDto) throws TvShowNotFoundException {
        LOGGER.info("Started method updateTvShow in TvShowController.");

        TvShow tvShow = tvShowRepository.findById(tvShowDto.getTvShowID()).orElseThrow(TvShowNotFoundException::new);
        tvShow.setTvShowTitle(tvShowDto.getTvShowTitle());
        tvShow.setTvShowCategory(tvShowDto.getTvShowCategory());
        tvShow.setTvShowRating(tvShowDto.getTvShowRating());
        TvShow updateTvShow = tvShowService.updateTvShow(tvShow);

        LOGGER.info("Ended method deleteTvShow in TvShowController.");

        return tvShowMapper.mapToTvShowDto(updateTvShow);
    }

    @DeleteMapping(value = "/deleteTvShow")
    public void deleteTvShow(@RequestParam Long tvShowID) throws TvShowNotFoundException {
        LOGGER.info("Started method deleteTvShow in TvShowController.");

        tvShowService.deleteTvShowByID(tvShowID);

        LOGGER.info("Ended method deleteTvShow in TvShowController.");
    }

    @DeleteMapping(value = "/deleteAllTvShows")
    public void deleteAllTvShows() {
        LOGGER.info("Started method deleteAllTvShows in TvShowController.");

        tvShowService.deleteAllTvShows();

        LOGGER.info("Ended method deleteAllTvShows in TvShowController.");
    }

    @GetMapping(value = "/getTvShowByCategory")
    public List<TvShowDto> getTvShowByCategory(@RequestParam String tvShowCategory) {
        LOGGER.info("Started method getTvShowByCategory in TvShowController.");

        return tvShowMapper.mapToTvShowDtoList(tvShowService.findTvShowByCategory(tvShowCategory));
    }

    @GetMapping(value = "/getTvShowByRating")
    public List<TvShowDto> getTvShowByRating(@RequestParam String tvShowRating) {
        LOGGER.info("Started method getTvShowByRating in TvShowController.");

        return tvShowMapper.mapToTvShowDtoList(tvShowService.findTvShowByRating(tvShowRating));
    }

    @GetMapping(value = "/countAllTvShows")
    public Long countAllTvShows() {
        LOGGER.info("Started method countAllTvShows in TvShowController.");
        long allTvShows = tvShowService.countAllTvShows();

        LOGGER.info("Ended method countAllTvShows in TvShowController.");
        return allTvShows;
    }
}