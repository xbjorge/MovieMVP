package com.example.movieadventure.homemodule.view.adapters;

import android.widget.ImageView;

import com.example.movieadventure.common.pojos.TopRatedMovie;

public interface MovieItemClickListener {

    void onMovieClickListener(TopRatedMovie topRatedMovie, ImageView moviePosterImageView);
}
