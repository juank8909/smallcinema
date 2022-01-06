package com.smallcinema.persistence.repository;

import com.smallcinema.api.dto.Error;
import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Repository {

    Either<ServiceError, Movie> updateTimeShowsAndPrices(Movie movie);

    Either<ServiceError, Option<Movie>> getMovie(String movieId);

    Either<ServiceError, Movie> rateMovie(String movieRate, String movieId);

}
