package com.smallcinema.domain.service;


import com.smallcinema.api.dto.Error;
import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.dto.MovieRecord;
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

    public Either<ServiceError, Movie> updateShowTimesAndPrices(Movie movie){
       // return dbRepository.updateTimeShowsAndPrices(movie);
        return null;
    }

    public Either<ServiceError, Option<MovieRecord>> getMovie(String movieId){
        return Either.right( dbRepository.getMovie(movieId));
    }

    public Either<ServiceError, Movie> rateMovie(String rate, String movieId){
        //return dbRepository.rateMovie(rate, movieId);
        return null;
    }
}
