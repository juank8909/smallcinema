package com.smallcinema.controllers;

import com.smallcinema.dto.ImmutableMovieDTO;
import com.smallcinema.dto.MovieDTO;
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


@RestController
@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesApi {
    private static final Logger log = LoggerFactory.getLogger(MoviesApi.class);

    @Operation(summary = "Find movie by id", description = "Returns a single movie", operationId = "getMovieById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = Error.class)))})
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("movieId") String consumerId) {
        return ResponseEntity.ok(ImmutableMovieDTO
                .builder()
                .name("first")
                .rating("5 stars")
                .description("first movie")
                .build());
    }

}
