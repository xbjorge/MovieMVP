package com.example.movieadventure.homemodule.model;

import com.example.movieadventure.common.KeyDirectory;
import com.example.movieadventure.common.pojos.TopRatedMovie;
import com.example.movieadventure.homemodule.DetailsActivityMvp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsInteractorClass implements DetailsActivityMvp.model {

    private DetailsActivityMvp.prsenter prsenter;
    private String titleMovie, descriptionMovie, dateMovie, dateMovieFormatter, urlPosterMoviw, urlBackdropMovie;
    private String imputFormatter = "yyyy-MM-dd";
    private String ouputFormatter = "MMM' 'dd'th, 'yyyy";

    public DetailsInteractorClass(DetailsActivityMvp.prsenter prsenter) {
        this.prsenter = prsenter;
    }

    @Override
    public void getMovieTopRated(TopRatedMovie topRatedMovie) {
        titleMovie = topRatedMovie.getTitle();
        descriptionMovie = topRatedMovie.getOverview();
        dateMovie = topRatedMovie.getReleaseDate();
        dateMovieFormatter = formatterDate(dateMovie);
        urlPosterMoviw = KeyDirectory.getUrlBaseImage()+topRatedMovie.getPosterPath();
        urlBackdropMovie = KeyDirectory.getUrlBaseImage()+topRatedMovie.getBackdropPath();

        prsenter.onResponseDataFromModel(titleMovie, descriptionMovie, dateMovieFormatter, urlPosterMoviw, urlBackdropMovie);
    }

    private String formatterDate(String RealeseDate){

        DateFormat getInputFormatter = new SimpleDateFormat(imputFormatter);
        Date date1 = null;
        try {
            date1 = getInputFormatter.parse(RealeseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat setOutputFormatter = new SimpleDateFormat(ouputFormatter);
        String output1 = setOutputFormatter.format(date1);
        return output1;
    }
}
