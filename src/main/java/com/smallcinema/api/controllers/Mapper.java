package com.smallcinema.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallcinema.api.dto.MovieDTO;
import com.smallcinema.api.dto.MovieShowTimesDTO;
import com.smallcinema.api.dto.OMDbMovieDTO;
import com.smallcinema.client.dto.OMDbMovieClientDTO;
import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.util.function.Function;

public class Mapper {

    public static final Function<Option<Movie>, Option<MovieShowTimesDTO>> movieToMovieShowTimesDTO = opt -> (
            opt.map(it -> new MovieShowTimesDTO(
                    it.getTitle(),
                    it.getShowTimes().toJavaList()
            ))
    );
    public static final Function<Option<Movie>, Option<MovieDTO>> movieToMovieDTO = opt -> (
            opt.map(it -> new MovieDTO(
                            it.getId(),
                            it.getTitle(),
                            it.getShowTimes().toJavaList(),
                            it.getPrice(),
                            it.getRate()
                    )
            )
    );
    public static final Function<Option<OMDbMovieClientDTO>, Option<OMDbMovieDTO>> movieFromClient = opt -> (
            opt.map(movie -> new OMDbMovieDTO(
                            movie.getTitle(),
                            movie.getPlot(),
                            movie.getReleased(),
                            movie.getImdbRating(),
                            movie.getRuntime()
                    )
            )
    );
    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    private Mapper() {
    }

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
