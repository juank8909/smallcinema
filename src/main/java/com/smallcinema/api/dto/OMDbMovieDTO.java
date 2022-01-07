package com.smallcinema.api.dto;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Value.Immutable
public interface OMDbMovieDTO {

    String getTitle();

    String getPlot();

    String getReleased();

    String getIMDbRating();

    String getRunTime();

}
