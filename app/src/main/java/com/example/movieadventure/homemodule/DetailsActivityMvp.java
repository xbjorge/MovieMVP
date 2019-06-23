package com.example.movieadventure.homemodule;

import com.example.movieadventure.common.pojos.TopRatedMovie;

import java.util.List;

public interface DetailsActivityMvp {

    interface view {
        void setDataToDetails(String titleMovie, String descriptionMovie, String dateMovie, String urlPosterMoviw, String urlBackdropMovie);
        void setAnimationToBackdropImageView();
        void showProgressBar();
        void hideProgressBar();
        void onFailureGetData(String errorMessage);
    }
    interface prsenter {
        void onDestroy();
        void onRequestDataFromModel(TopRatedMovie topRatedMovie);
        void onResponseDataFromModel(String titleMovie, String descriptionMovie, String dateMovie, String urlPosterMoviw, String urlBackdropMovie);
    }
    interface model {
        void getMovieTopRated(TopRatedMovie topRatedMovie);
    }
}
