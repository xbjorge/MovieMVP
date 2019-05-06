package com.example.movieadventure.homemodule.model;

import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.http.ServiceManager;

public class GetMovieInteractorClass implements HomeActivityMvp.GetMovieInteractor {

    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    @Override
    public void getMovieArrayLit(final onFinishedListener onFinishedListener) {
        ServiceManager serviceManager = new ServiceManager(BASE_URL);
        serviceManager.getMovies(onFinishedListener);
    }
}
