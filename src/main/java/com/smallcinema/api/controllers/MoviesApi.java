package com.smallcinema.api.controllers;

import com.smallcinema.api.dto.IMDbMovieDTO;
import com.smallcinema.api.dto.ImmutableIMDbMovieDTO;
import com.smallcinema.api.dto.ImmutableMovieDTO;
import com.smallcinema.api.dto.MovieDTO;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.function.Function;


@RestController
@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesApi {
    private static final Logger log = LoggerFactory.getLogger(MoviesApi.class);

    private final MovieService movieService;

    @Inject
    public MoviesApi(MovieService movieService){
        this.movieService = movieService;
    }

    @Operation(summary = "Find movie by id", description = "Returns a single movie", operationId = "getMovieById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<IMDbMovieDTO> getMovieById(@PathVariable("movieId") String movieId) {
        return ResponseEntity.ok(ImmutableIMDbMovieDTO
                .builder()
                .name("first")
                .rating("5 stars")
                .description("first movie")
                .releaseDate("22 Jun 2001")
                .iMDbRating("6/10")
                .runTime("2 hours")
                .build());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/movietimes/{movieId}")
    public ResponseEntity<ImmutableMovieDTO> getMovieTimesById(@PathVariable("movieId") String movieId) {
        return this.movieService.getMovie(movieId)
                .map(a -> a.map(movie -> ImmutableMovieDTO
                        .builder()
                        .name(movie.getTitle())
                        .showTimes(movie.getShowTimes())
                        .price(movie.getPrice())
                        .build()
                ))
                .map(responseBody -> responseBody.map(ResponseEntity::ok).getOrElse(ResponseEntity.notFound().build()))
                .getOrElseThrow(Function.identity());
    }

    @ExceptionHandler()
    public ResponseEntity<com.smallcinema.api.dto.Error> handleException(Exception exception) {
        if (exception instanceof ServiceError) {
            return ResponseEntity.internalServerError()
                    .body(new com.smallcinema.api.dto.Error(((ServiceError) exception).getCode(), exception.getMessage()));
        } else {
            return ResponseEntity.internalServerError().body(new com.smallcinema.api.dto.Error("SVCERR",
                    "An unexpected error has occurred while trying to fulfill your request. Please try again. If this error persists, please check the internal logs"));

        }
    }
}
