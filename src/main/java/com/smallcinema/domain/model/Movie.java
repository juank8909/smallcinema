package com.smallcinema.domain.model;

import io.vavr.collection.List;
import org.immutables.value.Value;

@Value.Immutable
public interface Movie {

    String getId();

    String getTitle();

    List<String> getShowTimes();

    Double getPrice();

    Integer getRate();
}
