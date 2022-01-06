package com.smallcinema.api.dto;

import io.vavr.collection.List;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Value.Immutable
public interface MovieTimesDTO {

    String getName();

    List<String> getShowTimes();

}
