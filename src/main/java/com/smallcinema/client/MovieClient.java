package com.smallcinema.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallcinema.client.dto.OMDbMovieClientDTO;
import com.smallcinema.domain.model.ServiceError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.OMDb-movies-url}${resource.OMDb-apiKey}&i=")
    private String resource;

    public Either<ServiceError, Option<OMDbMovieClientDTO>> getMovie(String movieId) {

        // TODO: do this using Future.of()
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(resource + movieId, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            if (root.path("Response").asText().equals("True"))
                return Either.right(Option.of(
                        new OMDbMovieClientDTO(
                                root.path("Title").asText(),
                                root.path("Plot").asText(),
                                root.path("Released").asText(),
                                root.path("imdbRating").asText(),
                                root.path("Runtime").asText()
                        )
                ));
            else return Either.left(new ServiceError("CLIERR", "An error occurred when trying to get the movie: " +
                    root.path("Error").asText()));
        } catch (JsonProcessingException e) {
            return Either.left(new ServiceError("CLIERR", "An error occurred when trying to deserialize the movie" +
                    e.getMessage()));
        }
    }

}
