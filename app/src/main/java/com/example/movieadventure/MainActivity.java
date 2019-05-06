package com.example.movieadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movieadventure.common.pojo.MovieList;
import com.example.movieadventure.common.pojo.Result;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.model.GetMovieInteractorClass;
import com.example.movieadventure.homemodule.presenter.HomeActivityPresenterClass;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeActivityMvp.view {

    private HomeActivityMvp.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Test all successfull

        presenter = new HomeActivityPresenterClass(this, new GetMovieInteractorClass());
        presenter.onRequestDataFromServer();
    }

    @Override
    public void setDataToSliderView(List<Result> resultArrayList) {
        Toast.makeText(getApplicationContext(), "Numero de peliculas: "+ resultArrayList.size(), Toast.LENGTH_LONG).show();
        for (Result result : resultArrayList){
            System.out.println(result.getTitle());
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this, "Error"+throwable, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
