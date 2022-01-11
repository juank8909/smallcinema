package com.smallcinema.persistence.repository;

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

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.smallcinema.persistence.dto.jooq.small_cinema.tables.Movie.MOVIE;

@org.springframework.stereotype.Repository
public class RepositoryImpl implements Repository {

    private final Logger log = LoggerFactory.getLogger(RepositoryImpl.class);

    private final DSLContext dsl;

    @Inject
    public RepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Either<ServiceError, Option<MovieRecord>> updateTimeShowsAndPrices(String movieId, List<String> showTimes, Double price) {

        return Either.right(Option.of(dsl.update(MOVIE)
                .set(MOVIE.SHOW_TIMES, showTimes.toJavaList().toArray(new String[0]))
                .set(MOVIE.PRICE, new BigDecimal(price))
                .where(MOVIE.ID.eq(movieId))
                .returningResult()
                .fetchOne(this::movieRecordFromJooq))
        );
    }

    @Override
    public Either<ServiceError, Option<MovieRecord>> getMovie(String movieId) {
        return Either.right(Option.ofOptional(dsl.select(MOVIE.fields())
                .from(MOVIE)
                .where(MOVIE.ID.equal(movieId))
                .fetch(this::movieRecordFromJooq)
                .stream().findFirst()
        ));
    }

    @Override
    public Either<ServiceError, Option<MovieRecord>> rateMovie(int movieRate, String movieId) {
        return Either.right(Option.of(dsl.update(MOVIE)
                .set(MOVIE.REVIEW, movieRate)
                .where(MOVIE.ID.eq(movieId))
                .returningResult()
                .fetchOne(this::movieRecordFromJooq))
        );
    }

    private MovieRecord movieRecordFromJooq(Record jooqRecord) {
        return ImmutableMovieRecord
                .builder()
                .title(jooqRecord.get(MOVIE.TITLE))
                .id(jooqRecord.get(MOVIE.ID))
                .rate(jooqRecord.get(MOVIE.REVIEW))
                .price((jooqRecord.get(MOVIE.PRICE).doubleValue()))
                .showTimes(List.ofAll(Arrays.stream(jooqRecord.get(MOVIE.SHOW_TIMES)).collect(Collectors.toList())).map(Object::toString))
                .build();
    }

}
