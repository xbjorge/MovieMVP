package com.example.movieadventure.homemodule;

import com.example.movieadventure.common.pojos.Result;
import com.example.movieadventure.common.pojos.TopRatedMovie;

import java.util.List;

public interface HomeActivityMvp {

    interface view{
        //this are methods of Slider pager
        void setDataToSliderView(List<Result> resultArrayList);
        void initSliderHomeMovies(List<Result> resultList);
        void onResponseFailure(Throwable throwable);
        void showPorgressBar();
        void hideProgressBar();

        //this are methods of Pager: The best ranking

        void setDataToPagerView(List<TopRatedMovie> resultsGetTopRatedMoviesList);
        void onResponseFailureGetDataToRated(Throwable errorToGetRated);
    }

    interface presenter{
        void onDestroy();
        void onRequestDataFromServer();
    }

    interface GetMovieInteractor {

        //this are methods of Slider pager
        interface onRequestGetToTopMovieListener {
            void onSuccessListenerGetMovie(List<Result> movieArrayList);
            void onFailureListenerGetMovie(Throwable throwable);
        }
        void getMovieArrayLit(onRequestGetToTopMovieListener onFinishedListener);

        //this are methods of Pager: The best ranking
        interface onRequestGetToTopRatedMovieListener{
            void onSuccessListenerGetTopRatedMovie(List<TopRatedMovie> movieTopRatedList);
            void onFailureListenerGetTopRatedMovie(Throwable errorGetTopRated);
        }
        void getTopRatedMovieList(onRequestGetToTopRatedMovieListener onFinishedListenerGetTopRatedMovie);

    }
}
