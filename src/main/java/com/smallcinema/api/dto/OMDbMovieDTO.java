package com.smallcinema.api.dto;

public class OMDbMovieDTO {

    private String title;

    private String plot;

    private String released;

    private String iMDbRating;

    private String runTime;

    public OMDbMovieDTO() {
    }

    public OMDbMovieDTO(String title, String plot, String released, String iMDbRating, String runTime) {
        this.title = title;
        this.plot = plot;
        this.released = released;
        this.iMDbRating = iMDbRating;
        this.runTime = runTime;
    }

    public String getTitle() {
        return title;
    }

    public String getPlot() {
        return plot;
    }

    public String getReleased() {
        return released;
    }

    public String getiMDbRating() {
        return iMDbRating;
    }

    public String getRunTime() {
        return runTime;
    }
}
