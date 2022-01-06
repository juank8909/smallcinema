package com.smallcinema.api.dto;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Value.Immutable
public interface IMDbMovieDTO {

    String getName();

    String getDescription();

    String getReleaseDate();

    String getRating();

    String getIMDbRating();

    String getRunTime();

}
