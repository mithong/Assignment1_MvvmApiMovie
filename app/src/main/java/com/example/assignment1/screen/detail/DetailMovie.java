package com.example.assignment1.screen.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment1.R;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.utils.FormatDate;
import com.example.assignment1.utils.LoadImage;

public class DetailMovie extends AppCompatActivity {

    ImageView imgMovieDetail, imgBack;
    TextView tvTitleDetail, tvDateDetail, tvOverviewDetail;
    RecyclerView rycGenres;
    DetailViewModel detailViewModel;
    GenresAdapter adapter;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initView();
        getIntents();
        initData();
        imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void initView() {
        imgBack = findViewById(R.id.imgBack);
        imgMovieDetail = findViewById(R.id.imgMovie);
        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        tvOverviewDetail = findViewById(R.id.tvOverViewDetail);
        rycGenres = findViewById(R.id.rycGenres);
    }

    private void getIntents() {
        Intent intent = getIntent();
        String MOVIE = "movie";
        movie = (Movie) intent.getSerializableExtra(MOVIE);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        if (movie != null) {
            tvTitleDetail.setText(movie.getTitle());
            tvOverviewDetail.setText(movie.getOverview());
            FormatDate formatDate = new FormatDate();
            tvDateDetail.setText(formatDate.formatDate(movie.getRelease_date()) + " - " + getResources().getString(R.string._2h_01m));
            LoadImage loadImage = new LoadImage();
            loadImage.loadImageFromURL(movie.getPoster_path(), imgMovieDetail, this);
            detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
            detailViewModel.getIdMovie(movie.getId());
        }
        LinearLayoutManager linearLayoutManagerImage = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rycGenres.setLayoutManager(linearLayoutManagerImage);

        detailViewModel.getGenresLiveData().observe(this, genres -> {
            adapter = new GenresAdapter(genres, this);
            rycGenres.setAdapter(adapter);
        });
    }
}