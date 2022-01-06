package com.smallcinema.api.dto;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Value.Immutable
public interface MovieDTO {

    String getName();

    @Nullable
    String getDescription();

    @Nullable
    OffsetDateTime getReleaseDate();

    @Nullable
    String getRating();

    @Nullable
    String getIMDbRating();

    @Nullable
    String getRunTime();

}
