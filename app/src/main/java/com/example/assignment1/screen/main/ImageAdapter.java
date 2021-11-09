package com.example.assignment1.screen.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.R;
import com.example.assignment1.data.model.Movie;
import com.example.assignment1.utils.LoadImage;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final List<Movie> image;
    private final ImageAdapter.OnItemImageCLick onItemClick;
    private Context context;

    public ImageAdapter(List<Movie> image, Context context, ImageAdapter.OnItemImageCLick onItemClick) {
        this.image = image;
        this.onItemClick = onItemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =
                inflater.inflate(R.layout.item_horizontal, parent, false);
        return new ImageAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {
        Movie it = image.get(position);
        LoadImage loadImage = new LoadImage();
        loadImage.loadImageFromURL(it.getPoster_path(), holder.img, context);
        holder.layout.setOnClickListener(v -> onItemClick.onClickImageItem(position));
    }

    @Override
    public int getItemCount() {
        if (image != null)
            return image.size();
        return 0;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ConstraintLayout layout;

        public ImageViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            layout = itemView.findViewById(R.id.layoutImage);
        }
    }

    interface OnItemImageCLick {
        void onClickImageItem(int position);
    }
}
