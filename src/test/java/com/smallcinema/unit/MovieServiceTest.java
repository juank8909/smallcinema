package com.smallcinema.unit;

import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.domain.service.MovieService;
import com.smallcinema.persistence.dto.ImmutableMovieRecord;
import com.smallcinema.persistence.dto.MovieRecord;
import com.smallcinema.persistence.repository.Repository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class MovieServiceTest {

    @Mock
    private Repository repo;

    @InjectMocks
    private MovieService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new MovieService(repo);
    }

    @Test
    @DisplayName("get movie")
    public void getMovie() {

        MovieRecord movie = ImmutableMovieRecord.builder()
                .id("1234")
                .title("Fast and Furious")
                .showTimes(List.of("WED 10:00 PM"))
                .price(10.0)
                .rate(4)
                .build();

        MovieRecord expectedMovie = ImmutableMovieRecord.builder()
                .id("1234")
                .title("Fast and Furious")
                .showTimes(List.of("WED 10:00 PM"))
                .price(10.0)
                .rate(4)
                .build();

        Mockito.when(repo.getMovie(anyString())).thenReturn(Either.right(Option.of(movie)));

        Either<ServiceError, Option<Movie>> movieResult = service.getMovie("1234");
        assertEquals(movieResult.get().get().getId(),expectedMovie.getId());
        assertEquals(movieResult.get().get().getTitle(),expectedMovie.getTitle());
        assertEquals(movieResult.get().get().getShowTimes(),expectedMovie.getShowTimes());
        assertEquals(movieResult.get().get().getPrice(),expectedMovie.getPrice());
        assertEquals(movieResult.get().get().getRate(),expectedMovie.getRate());
    }
}
