package com.example.movieadventure.homemodule.presenter;

import com.example.movieadventure.common.pojo.Result;
import com.example.movieadventure.homemodule.HomeActivityMvp;

import java.util.List;

public class HomeActivityPresenterClass implements HomeActivityMvp.presenter, HomeActivityMvp.GetMovieInteractor.onFinishedListener {

    private HomeActivityMvp.view homeView;
    private HomeActivityMvp.GetMovieInteractor getMovieInteractor;

    public HomeActivityPresenterClass(HomeActivityMvp.view homeView, HomeActivityMvp.GetMovieInteractor getMovieInteractor) {
        this.homeView = homeView;
        this.getMovieInteractor = getMovieInteractor;
    }

    @Override
    public void onDestroy() {
        homeView = null;
    }

    @Override
    public void onRequestDataFromServer() {
        getMovieInteractor.getMovieArrayLit(this);
    }

    @Override
    public void onSuccess(List<Result> movieArrayList) {
        if (homeView != null){
            homeView.setDataToSliderView(movieArrayList);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if ( homeView != null){
            homeView.onResponseFailure(throwable);
        }
    }
}
