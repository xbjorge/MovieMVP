package com.example.movieadventure.homemodule;

import com.example.movieadventure.common.pojos.Result;

import java.util.List;

public interface HomeActivityMvp {

    interface view{
        void setDataToSliderView(List<Result> resultArrayList);
        void initSliderHomeMovies(List<Result> resultList);
        void onResponseFailure(Throwable throwable);
        void showPorgressBar();
        void hideProgressBar();
    }

    interface presenter{
        void onDestroy();
        void onRequestDataFromServer();
    }

    interface GetMovieInteractor {

        interface onFinishedListener {
            void onSuccessListenerGetMovie(List<Result> movieArrayList);
            void onFailureListenerGetMovie(Throwable throwable);
        }
        void getMovieArrayLit(onFinishedListener onFinishedListener);
    }
}
