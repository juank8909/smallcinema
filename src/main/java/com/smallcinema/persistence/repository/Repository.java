package com.smallcinema.persistence.repository;

import com.smallcinema.api.dto.Error;
import com.smallcinema.domain.model.Movie;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Repository {

    Either<Error, Option<Movie>> updateTimeShowsAndPrices(Movie movie);

    Either<Error, Option<Movie>> getMovie(String movieId);

    Either<Error, Option<Movie>> rateMovie(String movieRate);

}
