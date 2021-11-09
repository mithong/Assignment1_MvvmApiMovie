package com.example.assignment1.data.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private final int id;
    private final String title;
    private final String overview;
    private final String poster_path;
    private final String release_date;
    private final double vote_average;

    public Movie(int id, String title, String overview, String poster_path, String release_date, double vote_average) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() {
        return vote_average;
    }
}
