package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.smallcinema.persistence.dto.jooq.small_cinema.tables.Movie.*;

import javax.inject.Inject;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {

    private final Logger log = LoggerFactory.getLogger(RepositoryImpl.class);

    private final DSLContext dsl;

    @Inject
    public RepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Either<ServiceError, Movie> updateTimeShowsAndPrices(Movie movie) {
        return null;
    }

    @Override
    public Either<ServiceError, Option<Movie>> getMovie(String movieId) {
        return dsl.select(MOVIE.fields())
                .from(MOVIE)
                .where(MOVIE.ID.equals(movieId));
    }

    @Override
    public Either<ServiceError, Movie> rateMovie(String movieRate, String movieId) {
        return null;
    }
}
