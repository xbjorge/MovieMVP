package com.example.movieadventure.homemodule.view;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieadventure.R;
import com.example.movieadventure.common.KeyDirectory;
import com.example.movieadventure.common.pojos.TopRatedMovie;
import com.example.movieadventure.homemodule.DetailsActivityMvp;
import com.example.movieadventure.homemodule.model.DetailsInteractorClass;
import com.example.movieadventure.homemodule.presenter.DetailsActivityPresenterClass;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements DetailsActivityMvp.view {

    private ImageView MovieImgPoster, movieImgBackdrop;
    private TextView movieTitleToolbar, movieTitleMovie, movieDescription, movieDate;
    private DetailsActivityMvp.prsenter prsenter;
    private TopRatedMovie topMvoie;
    private ImageButton movieFavoriteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        topMvoie = (TopRatedMovie) getIntent().getExtras().getSerializable("topRated");

        initializaTextViews();
        initializeImageViews();
        initializeImageButton();

        //movieFavoriteUser.setSelected(false);

        //Toast.makeText(this, "Success "+ topMvoie.getId(), Toast.LENGTH_SHORT).show();
        prsenter = new DetailsActivityPresenterClass(this);
        prsenter = new DetailsActivityPresenterClass(this, new DetailsInteractorClass(prsenter));
        prsenter.onRequestDataFromModel(topMvoie);

    }


    @Override
    public void setDataToDetails(String titleMovie, String descriptionMovie, String dateMovie, String urlPosterMoviw, String urlBackdropMovie) {
        movieTitleMovie.setText(titleMovie);
        movieTitleToolbar.setText(titleMovie);
        movieDescription.setText(descriptionMovie);
        movieDate.setText(dateMovie);
        final RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(urlPosterMoviw)
                .apply(options)
                .into(MovieImgPoster);
        Glide.with(this)
                .load(urlBackdropMovie)
                .apply(options)
                .into(movieImgBackdrop);
    }

    @Override
    public void setAnimationToBackdropImageView() {
        movieImgBackdrop.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onFailureGetData(String errorMessage) {
        Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        prsenter.onDestroy();
    }
    public void initializaTextViews(){
        movieTitleToolbar = findViewById(R.id.details_toolbar_title);
        movieTitleMovie = findViewById(R.id.details_tvTitleMovie);
        movieDescription = findViewById(R.id.details_tvDescriptionMovie);
        movieDate = findViewById(R.id.details_tvDateMovie);
    }
    public void initializeImageViews(){
        MovieImgPoster = findViewById(R.id.details_imvDetailsMoviePoster2);
        movieImgBackdrop = findViewById(R.id.details_imvBackdropMovie2);
    }
    public void initializeImageButton(){
        //movieFavoriteUser = findViewById(R.id.details_fabFavorite);
    }

}
