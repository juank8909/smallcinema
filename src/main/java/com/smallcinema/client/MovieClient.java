package com.smallcinema.client;

import com.smallcinema.client.dto.OMDbMovieClientDTO;
import com.smallcinema.domain.model.ServiceError;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.OMDb-movies-url}${resource.OMDb-apiKey}&i=")
    private String resource;

    public Either<ServiceError, Option<OMDbMovieClientDTO>> getMovie(String movieId){
        //todo: make it more functional without if
        // using Future.of(...)
        // .map(val -> Either.right(val))
        // .onFailure(err -> Either.left(new ServiceError("CLIERR, err.getMessage()"))) types are not matching
        Future<Either<ServiceError,Option<OMDbMovieClientDTO>>> future =
                Future.of(() -> Option.of(restTemplate.getForObject(resource + movieId, OMDbMovieClientDTO.class)))
                .map(val -> Either.right(val));
        return future.isFailure()
               ? Either.left(new ServiceError("CLIERR", "An error occurred when trying to get the movie"))
               : future.get();
    }

}
