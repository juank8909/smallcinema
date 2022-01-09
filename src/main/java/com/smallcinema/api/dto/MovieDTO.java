package com.smallcinema.api.dto;


import javax.annotation.Nullable;
import java.util.List;

public class MovieDTO {

    private String id;

    private String title;

    private List<String> showTimes;

    private Double price;

    private Integer rate;

    public MovieDTO() {
    }

    public MovieDTO(String id, String title, List<String> showTimes, Double price, Integer rate) {
        this.id = id;
        this.title = title;
        this.showTimes = showTimes;
        this.price = price;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getShowTimes() {
        return showTimes;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getRate() {
        return rate;
    }
}
