package com.example.movieadventure.homemodule.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movieadventure.R;
import com.example.movieadventure.common.pojo.Result;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.model.GetMovieInteractorClass;
import com.example.movieadventure.homemodule.presenter.HomeActivityPresenterClass;
import com.example.movieadventure.homemodule.view.adapters.HomeSliderMovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeActivityMvp.view {

    private HomeActivityMvp.presenter presenter;
    private ViewPager viewPagerSlider;
    private HomeSliderMovieAdapter adapterSlider;
    private TabLayout tabLayoutIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Test all successfull

        initializaViewPager();
        initializaTabIndicator();

        presenter = new HomeActivityPresenterClass(this, new GetMovieInteractorClass());
        presenter.onRequestDataFromServer();
    }

    @Override
    public void setDataToSliderView(List<Result> resultArrayList) {
        adapterSlider = new HomeSliderMovieAdapter(MainActivity.this, resultArrayList);
        viewPagerSlider.setAdapter(adapterSlider);
        tabLayoutIndicator.setupWithViewPager(viewPagerSlider,true);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this, "Error "+throwable, Toast.LENGTH_LONG).show();
        Log.d("slider", "",throwable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initializaViewPager(){
        viewPagerSlider = findViewById(R.id.home_viewPager_slider);
    }

    private void initializaTabIndicator(){
        tabLayoutIndicator = findViewById(R.id.home_indicator_pager_movie);
    }
}
