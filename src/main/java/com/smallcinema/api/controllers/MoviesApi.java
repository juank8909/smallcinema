package com.smallcinema.api.controllers;

import com.smallcinema.api.dto.ImmutableOMDbMovieDTO;
import com.smallcinema.api.dto.ImmutableMovieDTO;
import com.smallcinema.client.MovieClient;
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
import java.util.List;
import java.util.function.Function;


@RestController
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesApi {
    private static final Logger log = LoggerFactory.getLogger(MoviesApi.class);

    private final MovieService movieService;
    private final MovieClient movieClient;

    @Inject
    public MoviesApi(MovieService movieService, MovieClient movieClient){
        this.movieService = movieService;
        this.movieClient = movieClient;
    }

    @Operation(summary = "Find movie by id", description = "Returns a single movie", operationId = "getMovieById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<ImmutableOMDbMovieDTO> getMovieById(@PathVariable(value = "movieId") String movieId) {
        return movieClient.getMovie(movieId.substring(8))
                .map(val -> val.map(movie -> {
                    System.out.println(movie.getTitle());
                    return ImmutableOMDbMovieDTO
                        .builder()
                        .title(movie.getTitle())
                        .plot(movie.getPlot())
                        .released(movie.getReleased())
                        .iMDbRating(movie.getImdbRating())
                        .runTime(movie.getRuntime())
                        .build();}))
                .map(responseBody -> responseBody.map(ResponseEntity::ok).getOrElse(ResponseEntity.notFound().build()))
                .getOrElseThrow(Function.identity());
    }

    @Operation(summary = "Find movie times by id", description = "Returns a single movie times", operationId = "getMovieTimesById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/movietimes/{movieId}")
    public ResponseEntity<ImmutableMovieDTO> getMovieTimesById(@PathVariable(value = "movieId") String movieId) {
        return this.movieService.getMovie(movieId)
                .map(a -> a.map(movie -> ImmutableMovieDTO
                        .builder()
                        .name(movie.getTitle())
                        .showTimes( movie.getShowTimes())
                        .price(23.0)
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
