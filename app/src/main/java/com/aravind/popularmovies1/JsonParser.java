package com.aravind.popularmovies1;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    private static final String ID = "id";
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String RATING = "vote_average";
    private static final String POPULARITY = "popularity";
    private static final String POSTER_PATH = "poster_path";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String PLOT = "overview";
    private static final String VOTES = "vote_count";

    public static ArrayList<Movie> parse(String jsonData) {

        ArrayList<Movie> movieList = new ArrayList<>();

        if(jsonData==null)
            return movieList;

        try {
            JSONArray movieArray = new JSONObject(jsonData).getJSONArray(RESULTS);
            int length = movieArray.length();

            for(int i=0; i<length; i++){

                Movie movie = new Movie();
                JSONObject movieVO = movieArray.getJSONObject(i);

                movie.setTitle(movieVO.getString(TITLE));
                movie.setId(movieVO.getString(ID));
                movie.setOverview(movieVO.getString(PLOT));
                movie.setPosterPath(movieVO.getString(POSTER_PATH));
                movie.setBackdropPath(movieVO.getString(BACKDROP_PATH));
                movie.setReleaseDate(movieVO.getString(RELEASE_DATE));
                movie.setPopularity(movieVO.getString(POPULARITY));
                movie.setRating(movieVO.getString(RATING));
                movie.setVoteCount(movieVO.getString(VOTES));

                movieList.add(movie);
            }
        } catch (JSONException e) {
            Log.e("JSONParser","Exception occurred in parsing JSON");
        }

        return movieList;
    }
}
