package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.TvShow;
import com.kodilla.eprojectkbackend.domains.TvShowDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TvShowMapper {

    public TvShow mapToTvShow(final TvShowDto tvShowDto) {
        return new TvShow(
                tvShowDto.getTvShowID(),
                tvShowDto.getTvShowTitle(),
                tvShowDto.getTvShowCategory(),
                tvShowDto.getTvShowRating(),
                tvShowDto.getTvShowCreated()
        );
    }

    public TvShowDto mapToTvShowDto(final TvShow tvShow) {
        return new TvShowDto(
                tvShow.getTvShowID(),
                tvShow.getTvShowTitle(),
                tvShow.getTvShowCategory(),
                tvShow.getTvShowRating(),
                tvShow.getTvShowCreated()
        );
    }

    public List<TvShowDto> mapToTvShowDtoList(final List<TvShow> tvShowList) {
        return tvShowList.stream()
                .map(this::mapToTvShowDto)
                .collect(Collectors.toList());
    }

    public List<TvShow> mapToTvShowList(final List<TvShowDto> tvShowDtoList) {
        return tvShowDtoList.stream()
                .map(this::mapToTvShow)
                .collect(Collectors.toList());
    }
}