package com.smallcinema.client.dto;

public class OMDbMovieDTO {

    private String title;
    private String plot;
    private String released;
    private String imdbRating;
    private String runtime;

    public OMDbMovieDTO() {
    }

    public OMDbMovieDTO(String title, String plot, String released, String imdbRating, String runtime) {
        this.title = title;
        this.plot = plot;
        this.released = released;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
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

    public String getImdbRating() {
        return imdbRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
