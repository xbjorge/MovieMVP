package com.example.movieadventure.homemodule.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.movieadventure.R;
import com.example.movieadventure.common.pojos.Result;
import com.example.movieadventure.homemodule.HomeActivityMvp;
import com.example.movieadventure.homemodule.model.GetMovieInteractorClass;
import com.example.movieadventure.homemodule.presenter.HomeActivityPresenterClass;
import com.example.movieadventure.homemodule.view.adapters.HomeSliderMovieAdapter;

import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements HomeActivityMvp.view {

    private HomeActivityMvp.presenter presenter;
    private ViewPager viewPagerSlider;
    private HomeSliderMovieAdapter adapterSlider;
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

        /*
        CircleImageView imageView = findViewById(R.id.myImageontoolbar);

        final RequestOptions options = new RequestOptions()
                .optionalCircleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(MainActivity.this)
                .load(R.drawable.profile)
                .apply(options)
                .into(imageView);*/

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
}
