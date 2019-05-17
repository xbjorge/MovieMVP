package com.example.movieadventure.homemodule.http;

import com.example.movieadventure.common.KeyDirectory;
import com.example.movieadventure.common.pojos.MovieList;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager  {

    private TheMovieApiServices apiService;

    public ServiceManager(String URL_TEMP) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(loggingInterceptor).
                connectTimeout(1, TimeUnit.MINUTES).
                writeTimeout(1, TimeUnit.MINUTES).
                readTimeout(1, TimeUnit.MINUTES).build();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL_TEMP)
                .client(okHttpClient).build();
        apiService = retrofit.create(TheMovieApiServices.class);
    }
    public void getMovies(final HomeActivityMvp.GetMovieInteractor.onFinishedListener callbackGetMovie){
        Call<MovieList> call =  apiService.getTopMovie(KeyDirectory.getApyKey());
        call.enqueue(new retrofit2.Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                try {
                    callbackGetMovie.onSuccessListenerGetMovie(response.body().getResults());
                } catch (Throwable e) {
                    callbackGetMovie.onFailureListenerGetMovie(e);
                }
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                callbackGetMovie.onFailureListenerGetMovie(t);
            }
        });
    }
}
