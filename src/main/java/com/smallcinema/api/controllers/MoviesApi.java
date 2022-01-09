package com.smallcinema.api.controllers;

import com.smallcinema.api.dto.ImmutableOMDbMovieDTO;
import com.smallcinema.api.dto.MovieDTO;
import com.smallcinema.api.dto.MovieShowTimesDTO;
import com.smallcinema.api.dto.RateMovieDTO;
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

    @Operation(summary = "Find movie by id at OMDb API", description = "Returns a single movie", operationId = "getMovieById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<ImmutableOMDbMovieDTO> getMovieById(@PathVariable(value = "movieId") String movieId) {
        return movieClient.getMovie(movieId)
                .map(Mapper.movieFromClient)
                .map(responseBody -> responseBody.map(ResponseEntity::ok).getOrElse(ResponseEntity.notFound().build()))
                .getOrElseThrow(Function.identity());
    }

    @Operation(summary = "Updates show times and prices", description = "Returns a single movie", operationId = "updateShowTimesAndPricesMovieId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> updateShowTimesAndPricesMovieId(@RequestBody MovieDTO movie, @PathVariable(value = "movieId") String movieId) {
        return movieService.updateShowTimesAndPrices(Mapper.movieDTOFToMovie(movie, movieId))
                .map(Mapper.movieToMovieDTO)
                .map(responseBody -> responseBody.map(ResponseEntity::ok).getOrElse(ResponseEntity.notFound().build()))
                .getOrElseThrow(Function.identity());
    }

    @Operation(summary = "Rates a movie", description = "Returns a single movie", operationId = "rateMovie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping("/{movieId}/rate")
    public ResponseEntity<MovieDTO> rateMovie(@RequestBody RateMovieDTO movie, @PathVariable(value = "movieId") String movieId) {
        return movieService.rateMovie(movie.getRate(), movieId)
                .map(Mapper.movieToMovieDTO)
                .map(responseBody -> responseBody.map(ResponseEntity::ok).getOrElse(ResponseEntity.notFound().build()))
                .getOrElseThrow(Function.identity());
    }

    @Operation(summary = "Find movie times by id", description = "Returns a single movie times", operationId = "getMovieTimesById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{movieId}/times")
    public ResponseEntity<MovieShowTimesDTO> getMovieTimesById(@PathVariable(value = "movieId") String movieId) {
        return this.movieService.getMovie(movieId)
                .map(Mapper.movieToMovieShowTimesDTO)
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
