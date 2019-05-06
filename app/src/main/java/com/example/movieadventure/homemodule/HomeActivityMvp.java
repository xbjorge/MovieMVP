package com.example.movieadventure.homemodule;

import com.example.movieadventure.common.pojo.Result;
import java.util.List;

public interface HomeActivityMvp {

    interface view{
        void setDataToSliderView(List<Result> resultArrayList);
        void onResponseFailure(Throwable throwable);
    }

    interface presenter{
        void onDestroy();
        void onRequestDataFromServer();
    }

    interface GetMovieInteractor {

        interface onFinishedListener {
            void onSuccess(List<Result> movieArrayList);
            void onFailure(Throwable throwable);
        }
        void getMovieArrayLit(onFinishedListener onFinishedListener);
    }
}
