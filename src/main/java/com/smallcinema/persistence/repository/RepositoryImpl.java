package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {

    private final Logger log = LoggerFactory.getLogger(RepositoryImpl.class);

    @Override
    public Either<ServiceError, Movie> updateTimeShowsAndPrices(Movie movie) {
        return null;
    }

    @Override
    public Either<ServiceError, Option<Movie>> getMovie(String movieId) {
        return Either.right(Option.of(ImmutableMovie
                .builder()
                .title("first Movie")
                .id("123")
                .price(20.3)
                .review("excelent movie")
                .showTimes(List.of("wed 20:00"))
                .build()));
    }

    @Override
    public Either<ServiceError, Movie> rateMovie(String movieRate, String movieId) {
        return null;
    }
}
