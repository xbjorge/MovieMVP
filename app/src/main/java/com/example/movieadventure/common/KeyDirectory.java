package com.example.movieadventure.common;

public class KeyDirectory {

    static final String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/original";
    static final String APY_KEY = "559053a6af29171c879dfd14e7bc20b6";
    static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    public static String getBaseUrl() { return BASE_URL; }
    public static String getApyKey() { return APY_KEY; }
    public static String getUrlBaseImage() {
        return URL_BASE_IMAGE;
    }
}
