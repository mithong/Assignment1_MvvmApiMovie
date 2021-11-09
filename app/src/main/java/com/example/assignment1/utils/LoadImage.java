package com.example.assignment1.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.assignment1.R;

import static com.example.assignment1.utils.Constant.URLLOADINGIMAGE;

public class LoadImage {

    public void loadImageFromURL(String url, ImageView imageView, Context context) {
        Glide.with(context.getApplicationContext())
                .load(URLLOADINGIMAGE + url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
