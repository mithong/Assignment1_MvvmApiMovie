package com.example.assignment1.data.source;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.data.source.remote.OnFetchDataJsonListener;

import java.util.ArrayList;

public interface MovieDataSource {

    interface Remote {
        void getMoviePopular(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener);

        void getMovieNowPlaying(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener);

        void getGenres(int idMovie, OnFetchDataJsonListener<ArrayList<Genres>> listener);
    }
}
