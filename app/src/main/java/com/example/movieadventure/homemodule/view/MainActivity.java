package com.example.movieadventure.homemodule.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.movieadventure.R;
import com.example.movieadventure.common.pojos.Result;
import com.example.movieadventure.common.pojos.TopRatedMovie;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.model.GetMovieInteractorClass;
import com.example.movieadventure.homemodule.presenter.HomeActivityPresenterClass;
import com.example.movieadventure.homemodule.view.adapters.HomeSliderMovieAdapter;
import com.example.movieadventure.homemodule.view.adapters.HomeTopRatedMovieThreePager;
import com.example.movieadventure.homemodule.view.adapters.MovieItemClickListener;

import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements HomeActivityMvp.view, MovieItemClickListener {

    private HomeActivityMvp.presenter presenter;
    private ViewPager viewPagerSlider;
    private RecyclerView viewRecyclerTrheePager;
    private HomeSliderMovieAdapter adapterSlider;
    private HomeTopRatedMovieThreePager adapter3Pager;
    private TabLayout tabLayoutIndicator;
    private ProgressBar progressBarGetSliderMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Test all successfull

        initializaViewPager();
        initializaTabIndicator();
        initializaProgressBar();
        initializaToolBar();
        initializaRecyclerView();

        presenter = new HomeActivityPresenterClass(this, new GetMovieInteractorClass());
        presenter.onRequestDataFromServer();


    }

    @Override
    public void setDataToSliderView(List<Result> resultArrayList) {
        adapterSlider = new HomeSliderMovieAdapter(MainActivity.this, resultArrayList);
        viewPagerSlider.setOffscreenPageLimit(6);
        viewPagerSlider.setAdapter(adapterSlider);
        tabLayoutIndicator.setupWithViewPager(viewPagerSlider,true);
    }

    @Override
    public void initSliderHomeMovies(List<Result> resultList) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivityPresenterClass
                .SliderTimer(this, resultList, viewPagerSlider),4000,6000);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this, R.string.home_error_conection, Toast.LENGTH_LONG).show();
        Log.d("slider_home", "",throwable);
    }

    @Override
    public void showPorgressBar() {
        progressBarGetSliderMovies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarGetSliderMovies.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToPagerView(List<TopRatedMovie> resultsGetTopRatedMoviesList) {
        adapter3Pager = new HomeTopRatedMovieThreePager(MainActivity.this, resultsGetTopRatedMoviesList,this);
        //Toast.makeText(this, adapter3Pager.getItemCount(), Toast.LENGTH_LONG).show();
        viewRecyclerTrheePager.setAdapter(adapter3Pager);
        viewRecyclerTrheePager.setPadding(16, 4, 0, 0);
        viewRecyclerTrheePager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onResponseFailureGetDataToRated(Throwable errorToGetRated) {
        Toast.makeText(MainActivity.this, R.string.home_error_conection, Toast.LENGTH_LONG).show();
        Log.d("slider_home", "",errorToGetRated);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initializaViewPager(){
        viewPagerSlider = findViewById(R.id.home_viewPager_slider);
    }
    private void initializaRecyclerView(){
        viewRecyclerTrheePager = findViewById(R.id.home_viewpager_pager3Movies);
    }

    private void initializaTabIndicator(){
        tabLayoutIndicator = findViewById(R.id.home_indicator_pager_movie);
    }
    private void initializaProgressBar(){
        progressBarGetSliderMovies = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBarGetSliderMovies.setIndeterminate(true);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBarGetSliderMovies);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBarGetSliderMovies.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }
    private void initializaToolBar(){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClickListener(TopRatedMovie topRatedMovie, ImageView moviePosterImageView) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("topRated", topRatedMovie);
        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                     moviePosterImageView,"sharedName");
        }
        startActivity(intent,options.toBundle());
    }
}
