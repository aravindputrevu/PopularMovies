package com.aravind.popularmovies1;


import android.net.Uri;

public class Util {

    public static String constructAPIURL(String sortBy, String page) {
        return Uri.parse(Constants.FETCH_MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(Constants.SORT_PARAM, sortBy)
                .appendQueryParameter(Constants.PAGE_PARAM, page)
                .appendQueryParameter(Constants.API_KEY_PARAM, BuildConfig.THE_MOVIE_DB_API_KEY)
                .build().toString();
    }
}
