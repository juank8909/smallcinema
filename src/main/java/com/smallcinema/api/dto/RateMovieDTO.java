package com.smallcinema.api.dto;


public class RateMovieDTO {

    private int rate;

    public RateMovieDTO() {
    }

    public RateMovieDTO(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
