package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.dto.ImmutableMovieRecord;
import com.smallcinema.persistence.dto.MovieRecord;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.smallcinema.persistence.dto.jooq.small_cinema.tables.Movie.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {

    private final Logger log = LoggerFactory.getLogger(RepositoryImpl.class);

    private final DSLContext dsl;

    @Inject
    public RepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Either<ServiceError, MovieRecord> updateTimeShowsAndPrices(Movie movie) {
        return null;
    }

    @Override
    public Either<ServiceError, Option<MovieRecord>> getMovie(String movieId) {
       return Either.right( Option.ofOptional(dsl.select(MOVIE.fields())
                .from(MOVIE)
                .where(MOVIE.ID.equal(movieId))
                .fetch(this::movieRecordFormJooq )
       .stream().findFirst()
       ));
    }

    @Override
    public Either<ServiceError, MovieRecord> rateMovie(String movieRate, String movieId) {
        return null;
    }

    private MovieRecord movieRecordFormJooq(Record jooqRecord){
        return ImmutableMovieRecord
                .builder()
                .title(jooqRecord.get(MOVIE.TITLE))
                .id(jooqRecord.get(MOVIE.ID))
                .review(jooqRecord.get(MOVIE.REVIEW).toString())
                .price((jooqRecord.get(MOVIE.PRICE).doubleValue()))
                .showTimes(List.ofAll(Arrays.stream(jooqRecord.get(MOVIE.SHOW_TIMES)).collect(Collectors.toList())).map(Object::toString))
                .build();
    }

}
