package com.smallcinema.api.dto;

import io.vavr.collection.List;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.OffsetDateTime;

@Value.Immutable
public interface MovieDTO {

    String getName();

    List<String> getShowTimes();

    @Nullable
    Long  getPrice();

}
