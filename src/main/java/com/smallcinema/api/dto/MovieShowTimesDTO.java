package com.smallcinema.api.dto;

import java.util.List;

public class MovieShowTimesDTO {

    private String title;

    private List<String> showTimes;

    public MovieShowTimesDTO() {
    }

    public MovieShowTimesDTO(String title, List<String> showTimes) {
        this.title = title;
        this.showTimes = showTimes;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getShowTimes() {
        return showTimes;
    }
}
