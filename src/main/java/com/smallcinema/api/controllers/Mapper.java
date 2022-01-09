package com.smallcinema.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallcinema.api.dto.*;
import com.smallcinema.client.dto.OMDbMovieDTO;
import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.util.function.Function;

public class Mapper {

    private Mapper() {
    }

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public static final Function<Option<Movie>, Option<MovieShowTimesDTO>> movieToMovieShowTimesDTO = opt -> (
            opt.map(it-> ImmutableMovieShowTimesDTO
                            .builder()
                            .name(it.getTitle())
                            .showTimes(it.getShowTimes())
                            .build()
                    )
    );

    public static final Function<Option<Movie>, Option<MovieDTO>> movieToMovieDTO = opt -> (
            opt.map(it-> new MovieDTO(
                    it.getId(),
                    it.getTitle(),
                    it.getShowTimes().toJavaList(),
                    it.getPrice(),
                    it.getRate()
                    )
            )
    );

    public static final Function<Option<OMDbMovieDTO>, Option<ImmutableOMDbMovieDTO>> movieFromClient = opt -> (
            opt.map(movie-> ImmutableOMDbMovieDTO
                    .builder()
                    .title(movie.getTitle())
                    .plot(movie.getPlot())
                    .released(movie.getReleased())
                    .iMDbRating(movie.getImdbRating())
                    .runTime(movie.getRuntime())
                    .build()
            )
    );

    public static final Movie movieDTOFToMovie(MovieDTO movie, String movieId) {
       return ImmutableMovie
                .builder()
                .title(movie.getId())
                .id(movieId)
                .price(movie.getPrice())
                .rate(movie.getRate())
                .showTimes(List.ofAll(movie.getShowTimes()))
                .build();
    }

}
