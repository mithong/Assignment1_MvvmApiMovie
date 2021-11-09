package com.example.assignment1.data.source;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.data.source.remote.OnFetchDataJsonListener;
import com.example.assignment1.data.source.remote.RemoteDataSource;

import java.util.ArrayList;

public class MovieRepository {

    private final MovieDataSource.Remote remote;
    private static MovieRepository INSTANCE = null;

    public MovieRepository(MovieDataSource.Remote remote) {
        this.remote = remote;
    }

    public void getMoviesPopular(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener) {
        remote.getMoviePopular(page, listener);
    }

    public void getMoviesNowPlaying(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener) {
        remote.getMovieNowPlaying(page, listener);
    }

    public void getGenres(int idMovie, OnFetchDataJsonListener<ArrayList<Genres>> listener) {
        remote.getGenres(idMovie, listener);
    }

    public static synchronized MovieRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(RemoteDataSource.getInstance());
        }
        return (INSTANCE);
    }
}
