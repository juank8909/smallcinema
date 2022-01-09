package com.smallcinema.persistence.repository;

import com.smallcinema.domain.model.ImmutableMovie;
import com.smallcinema.domain.model.Movie;
import com.smallcinema.domain.model.ServiceError;
import com.smallcinema.persistence.dto.ImmutableMovieRecord;
import com.smallcinema.persistence.dto.MovieRecord;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.Schema;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.smallcinema.persistence.dto.jooq.tables.Movie.*;

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
    public Option<MovieRecord> getMovie(String movieId) {
       return Option.ofOptional(dsl.select(MOVIE.fields())
                .from(MOVIE)
                .where(MOVIE.ID.equal(movieId))
                .fetch(jooqRecord -> ImmutableMovieRecord
                        .builder()
                        .title(jooqRecord.get(MOVIE.TITLE))
                        .id(jooqRecord.get(MOVIE.ID))
                        .review(jooqRecord.get(MOVIE.REVIEW).toString())
                        .showTimes(List.ofAll(Arrays.stream(jooqRecord.get(MOVIE.SHOW_TIMES.cast(String[].class))).collect(Collectors.toList())))
                        .build())
       .stream().findFirst()
       );
    }

    @Override
    public Either<ServiceError, MovieRecord> rateMovie(String movieRate, String movieId) {
        return null;
    }

}
