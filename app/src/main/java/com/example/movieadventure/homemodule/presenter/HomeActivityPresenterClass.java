package com.example.movieadventure.homemodule.presenter;

import android.support.v4.view.ViewPager;

import com.example.movieadventure.common.pojos.Result;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.view.MainActivity;

import java.util.List;
import java.util.TimerTask;

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
        if (homeView != null) {
            homeView.showPorgressBar();
        }
        getMovieInteractor.getMovieArrayLit(this);
    }

    @Override
    public void onSuccessListenerGetMovie(List<Result> movieArrayList) {
        if (homeView != null){
            homeView.setDataToSliderView(movieArrayList);
            homeView.initSliderHomeMovies(movieArrayList);
            homeView.hideProgressBar();
        }
    }

    @Override
    public void onFailureListenerGetMovie(Throwable throwable) {
        if ( homeView != null){
            homeView.onResponseFailure(throwable);
            homeView.hideProgressBar();
        }
    }
    public static class SliderTimer extends TimerTask{
        private MainActivity mainActivity;
        private List<Result> listResult;
        private ViewPager viewPager;

        public SliderTimer(MainActivity mainActivity, List<Result> listResult, ViewPager viewPager) {
            this.mainActivity = mainActivity;
            this.listResult = listResult;
            this.viewPager = viewPager;
        }

        @Override
        public void run() {
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()<listResult.size()-1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }
                    else
                        viewPager.setCurrentItem(0);
                }
            });
        }
    }
}
