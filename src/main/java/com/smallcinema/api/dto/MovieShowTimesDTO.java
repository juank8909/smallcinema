package com.smallcinema.api.dto;

import io.vavr.collection.List;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
public interface MovieShowTimesDTO {

    String getName();

    List<String> getShowTimes();

}
