package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.dto.MovieRecord;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Repository {

    Either<ServiceError, MovieRecord> updateTimeShowsAndPrices(Movie movie);

    Option<MovieRecord> getMovie(String movieId);

    Either<ServiceError, MovieRecord> rateMovie(String movieRate, String movieId);

}
