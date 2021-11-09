package com.example.assignment1.screen.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.source.MovieRepository;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {

    private final MovieRepository repository;
    private final MutableLiveData<ArrayList<Genres>> genresLiveData;
    private int idMovie;

    public DetailViewModel() {
        repository = MovieRepository.getInstance();
        genresLiveData = new MutableLiveData<>();
    }

    public void getIdMovie(int id) {
        this.idMovie = id;
        initData();
    }

    public MutableLiveData<ArrayList<Genres>> getGenresLiveData() {
        return genresLiveData;
    }

    private void initData() {
        repository.getGenres(idMovie, genresLiveData::setValue);
    }
}
