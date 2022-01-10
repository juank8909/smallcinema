package com.smallcinema.client.dto;

public class OMDbMovieClientDTO {

    private String Title;
    private String plot;
    private String released;
    private String imdbRating;
    private String runtime;

    public OMDbMovieClientDTO() {
    }

    public OMDbMovieClientDTO(String title, String plot, String released, String imdbRating, String runtime) {
        this.Title = title;
        this.plot = plot;
        this.released = released;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
