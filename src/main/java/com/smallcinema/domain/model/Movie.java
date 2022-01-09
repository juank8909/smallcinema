package com.smallcinema.domain.model;

import io.vavr.collection.List;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface Movie {

    String getId();

    String getTitle();

    List<String> getShowTimes();

    Double getPrice();

    Integer getRate();
}
