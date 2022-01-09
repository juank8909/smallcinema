package com.smallcinema.persistence.dto;

import io.vavr.collection.List;
import org.immutables.value.Value;


@Value.Immutable
public interface MovieRecord {
    String getId();

    String getTitle();

    List<String> getShowTimes();

    Double getPrice();

    Integer getRate();

}
