package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotvieDto;
import org.springframework.stereotype.Component;

@Component
public class MotiveMapper {

    public Motive mapToMotive(final MotvieDto motvieDto){
        return new Motive(
                motvieDto.getMotiveText(),
                motvieDto.getMotiveAuthor(),
                motvieDto.getMotiveRating()
        );
    }

    public MotvieDto mapToMotiveDto (final Motive motive){
        return new MotvieDto(
                motive.getMotiveID(),
                motive.getMotiveText(),
                motive.getMotiveAuthor(),
                motive.getMotiveRating()
        );
    }
}
