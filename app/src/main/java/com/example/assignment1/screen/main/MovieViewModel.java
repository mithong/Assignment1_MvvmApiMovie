package com.example.assignment1.screen.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment1.data.model.Movie;
import com.example.assignment1.data.source.MovieRepository;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private final MovieRepository repository;
    private final MutableLiveData<ArrayList<Movie>> moviePopularLiveData;
    private final MutableLiveData<ArrayList<Movie>> movieNowPlayingLiveData;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
        moviePopularLiveData = new MutableLiveData<>();
        movieNowPlayingLiveData = new MutableLiveData<>();
    }

    public void getStringPageMovie(String pageMovie) {
        initDataMovie(pageMovie);
    }

    public void getStringPageImage(String pageImage) {
        initDataImage(pageImage);
    }

    public MutableLiveData<ArrayList<Movie>> getMoviePopularLiveData() {
        return moviePopularLiveData;
    }

    public MutableLiveData<ArrayList<Movie>> getMovieNowPlayingLiveData() {
        return movieNowPlayingLiveData;
    }

    private void initDataMovie(String page) {
        repository.getMoviesPopular(page, moviePopularLiveData::setValue);
    }

    private void initDataImage(String page) {
        repository.getMoviesNowPlaying(page, movieNowPlayingLiveData::setValue);
    }
}
