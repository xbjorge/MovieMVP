package com.example.movieadventure.homemodule.model;

import com.example.movieadventure.common.KeyDirectory;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.http.ServiceManager;

public class GetMovieInteractorClass implements HomeActivityMvp.GetMovieInteractor {

    @Override
    public void getMovieArrayLit(final onFinishedListener onFinishedListener) {
        ServiceManager serviceManager = new ServiceManager(KeyDirectory.getBaseUrl());
        serviceManager.getMovies(onFinishedListener);
    }
}
