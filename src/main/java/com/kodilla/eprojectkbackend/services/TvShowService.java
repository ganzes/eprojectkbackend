package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.exceptions.TvShowNotFoundException;
import com.kodilla.eprojectkbackend.repositories.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvShowService {
    @Autowired
    private TvShowRepository tvShowRepository;

    public List<TvShow> getAllTvShow(){
        return tvShowRepository.findAll();
    }

    public TvShow findTvShowByID(long tvShowID) throws TvShowNotFoundException{
        return tvShowRepository.findById(tvShowID).orElseThrow(TvShowNotFoundException::new);
    }

    public TvShow createTvShow(final TvShow tvShow){
        return tvShowRepository.save(tvShow);
    }

    public TvShow updateTvShow(TvShow tvShow) throws TvShowNotFoundException {
        TvShow updateTvShow = tvShowRepository.findById(tvShow.getTvShowID()).orElseThrow(TvShowNotFoundException::new);
        updateTvShow.setTvShowTitle(tvShow.getTvShowTitle());
        updateTvShow.setTvShowCategory(tvShow.getTvShowCategory());
        updateTvShow.setTvShowRating(tvShow.getTvShowRating());

        return tvShowRepository.save(updateTvShow);
    }

    public void deleteTvShowByID(long tvShowID) throws TvShowNotFoundException{
        TvShow deleteTvShow = tvShowRepository.findById(tvShowID).orElseThrow(TvShowNotFoundException::new);
        tvShowRepository.delete(deleteTvShow);
    }

    public void deleteAllTvShows(){
        tvShowRepository.deleteAll();
    }

    public List<TvShow> findTvShowByCategory(String tvShowCategory) {
        return tvShowRepository.findByTvShowCategory(tvShowCategory);
    }

    public List<TvShow> findTvShowByRating(String tvShowRating) {
        return tvShowRepository.findByTvShowRating(tvShowRating);
    }

    public long countAllTvShows(){
        return tvShowRepository.count();
    }
}