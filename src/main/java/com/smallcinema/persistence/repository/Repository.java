package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.dto.MovieRecord;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface Repository {

    Either<ServiceError, Option<MovieRecord>> updateTimeShowsAndPrices(String movieId, List<String> showTimes, Double price);

    Either<ServiceError, Option<MovieRecord>> getMovie(String movieId);

    Either<ServiceError, Option<MovieRecord>> rateMovie(int movieRate, String movieId);

}
