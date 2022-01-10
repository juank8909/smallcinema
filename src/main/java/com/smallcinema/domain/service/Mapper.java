package com.smallcinema.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import com.smallcinema.persistence.dto.MovieRecord;
import io.vavr.control.Option;

import java.util.function.Function;

public class Mapper {

    public static final Function<Option<MovieRecord>, Option<Movie>> movieRecordToMovie = opt -> (
            opt.map(it -> ImmutableMovie
                    .builder()
                    .rate(it.getRate())
                    .id(it.getId())
                    .title(it.getTitle())
                    .showTimes(it.getShowTimes())
                    .price(it.getPrice().doubleValue())
                    .build()
            )
    );
    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    private Mapper() {
    }
}
