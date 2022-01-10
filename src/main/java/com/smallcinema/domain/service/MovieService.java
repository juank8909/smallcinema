package com.smallcinema.domain.service;


import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.repository.Repository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    private final Repository dbRepository;

    @Inject
    public MovieService(Repository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public Either<ServiceError, Option<Movie>> updateShowTimesAndPrices(Movie movie) {
        return dbRepository.updateTimeShowsAndPrices(movie.getId(), movie.getShowTimes(), movie.getPrice())
                .map(Mapper.movieRecordToMovie);
    }

    public Either<ServiceError, Option<Movie>> getMovie(String movieId) {
        return dbRepository.getMovie(movieId)
                .map(Mapper.movieRecordToMovie);
    }

    public Either<ServiceError, Option<Movie>> rateMovie(int rate, String movieId) {
        return dbRepository.rateMovie(rate, movieId)
                .map(Mapper.movieRecordToMovie);
    }
}
