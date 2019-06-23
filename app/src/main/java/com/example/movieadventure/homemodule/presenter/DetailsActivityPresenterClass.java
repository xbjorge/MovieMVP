package com.example.movieadventure.homemodule.presenter;

import com.example.movieadventure.common.pojos.TopRatedMovie;
import com.example.movieadventure.homemodule.DetailsActivityMvp;

public class DetailsActivityPresenterClass implements DetailsActivityMvp.prsenter {

    private DetailsActivityMvp.view detailsView;
    private DetailsActivityMvp.model detailsModel;

    public DetailsActivityPresenterClass(DetailsActivityMvp.view detailsView) {
        this.detailsView = detailsView;
    }

    public DetailsActivityPresenterClass(DetailsActivityMvp.view detailsView, DetailsActivityMvp.model detailsModel) {
        this.detailsView = detailsView;
        this.detailsModel = detailsModel;
    }

    @Override
    public void onDestroy() {
        detailsView = null;
    }

    @Override
    public void onRequestDataFromModel(TopRatedMovie topRatedMovie) {
        if (detailsView != null){
            //detailsView.showProgressBar();
        }
        detailsModel.getMovieTopRated(topRatedMovie);
    }

    @Override
    public void onResponseDataFromModel(String titleMovie, String descriptionMovie, String dateMovie, String urlPosterMoviw, String urlBackdropMovie) {
        if (detailsView != null){
            detailsView.setDataToDetails(titleMovie, descriptionMovie, dateMovie, urlPosterMoviw, urlBackdropMovie);
            detailsView.setAnimationToBackdropImageView();
        }
    }

}
