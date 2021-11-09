package com.example.assignment1.screen.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.R;
import com.example.assignment1.data.model.Genres;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private final List<Genres> genres;
    private Context context;

    public GenresAdapter(List<Genres> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }

    @NonNull
    @Override
    public GenresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =
                inflater.inflate(R.layout.item_genres, parent, false);
        return new GenresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresAdapter.ViewHolder holder, int position) {
        Genres it = genres.get(position);
        holder.tvGenres.setText(it.getName());
    }

    @Override
    public int getItemCount() {
        if (genres != null)
            return genres.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenres;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGenres = itemView.findViewById(R.id.tvGenres);
        }
    }
}
