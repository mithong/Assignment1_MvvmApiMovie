package com.example.assignment1.data.source.remote;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.data.source.MovieDataSource;
import com.example.assignment1.data.source.remote.fetchJson.GetJsonFromUrl;
import com.example.assignment1.data.source.remote.fetchJson.GetJsonGenres;

import java.util.ArrayList;

import static com.example.assignment1.utils.Constant.language;
import static com.example.assignment1.utils.Constant.url;
import static com.example.assignment1.utils.Constant.apiKey;
import static com.example.assignment1.utils.Constant.queryPopular;
import static com.example.assignment1.utils.Constant.keyEntity;
import static com.example.assignment1.utils.Constant.keyGenres;
import static com.example.assignment1.utils.Constant.queryNowPlaying;

public class RemoteDataSource implements MovieDataSource.Remote {
    private static RemoteDataSource INSTANCE = null;

    @Override
    public void getMoviePopular(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener) {
        GetJsonFromUrl getJsonFromUrl = new GetJsonFromUrl(listener, keyEntity);
        String urlPopular = url + queryPopular + apiKey + language + page;
        getJsonFromUrl.execute(urlPopular);
    }

    @Override
    public void getMovieNowPlaying(String page, OnFetchDataJsonListener<ArrayList<Movie>> listener) {
        GetJsonFromUrl getJsonFromUrl = new GetJsonFromUrl(listener, keyEntity);
        String urlNowPlaying = url + queryNowPlaying + apiKey + language + page;
        getJsonFromUrl.execute(urlNowPlaying);
    }

    @Override
    public void getGenres(int idMovie, OnFetchDataJsonListener<ArrayList<Genres>> listener) {
        GetJsonGenres getJsonGenres = new GetJsonGenres(listener, keyGenres);
        String urlGenres = url + idMovie + "?" + apiKey;
        getJsonGenres.execute(urlGenres);
    }

    public static synchronized RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return (INSTANCE);
    }
}
