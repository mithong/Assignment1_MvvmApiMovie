package com.example.assignment1.screen.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.assignment1.R;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.screen.detail.DetailMovie;
import com.example.assignment1.utils.OnScrollListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView rycMovie, rycImageMovie;
    ProgressBar prgBar, prgBarImage;
    MovieAdapter movieAdapter;
    ImageAdapter imageAdapter;
    MovieViewModel movieViewModel;
    LinearLayoutManager linearLayoutManagerImage;
    LinearLayoutManager linearLayoutManagerMovie;

    private boolean isLoading;
    private int currentPageMovie = 1;
    private int currentPageImage = 1;
    private final String page = "page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rycMovie = findViewById(R.id.rycMovie);
        rycImageMovie = findViewById(R.id.rycImageMovie);
        prgBar = findViewById(R.id.prg_bar);
        prgBarImage = findViewById(R.id.prg_barImage);
        linearLayoutManagerImage = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rycImageMovie.setLayoutManager(linearLayoutManagerImage);

        linearLayoutManagerMovie = new LinearLayoutManager(this);
        rycMovie.setLayoutManager(linearLayoutManagerMovie);
        initData();
    }

    private void initData() {
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getStringPageMovie(page + currentPageMovie);
        movieViewModel.getMoviePopularLiveData().observe(this, movies -> {
            movieAdapter = new MovieAdapter(movies, this, position -> intentToDetail(movies.get(position)));
            rycMovie.setAdapter(movieAdapter);
            rycMovie.addOnScrollListener(new OnScrollListener(linearLayoutManagerMovie) {
                @Override
                public void loadMoreItems() {
                    isLoading = true;
                    prgBar.setVisibility(View.VISIBLE);
                    currentPageMovie += 1;
                    movieViewModel.getStringPageMovie(page + currentPageMovie);
                    loadNextPage();
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
        });
        movieViewModel.getStringPageImage(page + currentPageImage);
        movieViewModel.getMovieNowPlayingLiveData().observe(this, movies -> {
            imageAdapter = new ImageAdapter(movies, this, position -> intentToDetail(movies.get(position)));
            rycImageMovie.setAdapter(imageAdapter);
            rycImageMovie.addOnScrollListener(new OnScrollListener(linearLayoutManagerImage) {
                @Override
                public void loadMoreItems() {
                    isLoading = true;
                    prgBarImage.setVisibility(View.VISIBLE);
                    currentPageImage += 1;
                    movieViewModel.getStringPageImage(page + currentPageImage);
                    loadNextPage();
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
        });
    }


    private void intentToDetail(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailMovie.class);
        String MOVIE = "movie";
        intent.putExtra(MOVIE, movie);
        startActivity(intent);
    }

    private void loadNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            isLoading = false;
            prgBar.setVisibility(View.GONE);
            prgBarImage.setVisibility(View.GONE);
        }, 2000);
    }
}