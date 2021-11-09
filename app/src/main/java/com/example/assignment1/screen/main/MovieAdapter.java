package com.example.assignment1.screen.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.R;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.utils.FormatDate;
import com.example.assignment1.utils.LoadImage;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> movie;
    private final OnItemMovieCLick onItemClick;
    private Context context;

    public MovieAdapter(List<Movie> movie, Context context, OnItemMovieCLick onItemClick) {
        this.movie = movie;
        this.onItemClick = onItemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =
                inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie it = movie.get(position);
        holder.tvTitle.setText(it.getTitle());
        holder.tvOverview.setText(it.getOverview());
        holder.layout.setOnClickListener(v -> onItemClick.onClickMovieItem(position));
        FormatDate formatDate = new FormatDate();
        holder.tvDate.setText(formatDate.formatDate(it.getRelease_date()));
        holder.tvRating.setText(it.getVote_average()*10 + "%");
        holder.prgRating.setProgress((int) (it.getVote_average()*10));
        LoadImage loadImage = new LoadImage();
        loadImage.loadImageFromURL(it.getPoster_path(), holder.imgMovie, context);
    }

    @Override
    public int getItemCount() {
        if (movie != null)
            return movie.size();
        return 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvOverview, tvRating;
        ImageView imgMovie;
        ProgressBar prgRating;
        ConstraintLayout layout;

        public MovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvOverview = itemView.findViewById(R.id.tvOverView);
            tvRating = itemView.findViewById(R.id.tvRating);
            imgMovie = itemView.findViewById(R.id.imgPath);
            layout = itemView.findViewById(R.id.layoutMovie);
            prgRating = itemView.findViewById(R.id.prgRating);
        }
    }

    interface OnItemMovieCLick {
        void onClickMovieItem(int position);
    }
}
