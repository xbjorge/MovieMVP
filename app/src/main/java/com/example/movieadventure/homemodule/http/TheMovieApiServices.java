package com.example.movieadventure.homemodule.http;

import com.example.movieadventure.common.pojo.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieApiServices {

    @GET("popular")
    Call<MovieList> getTopMovie(@Query("api_key") String apyKey);
}
