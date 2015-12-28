package com.aravind.popularmovies1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie movie = getIntent().getParcelableExtra(getString(R.string.moveObject));

        ImageView big_poster = (ImageView) findViewById(R.id.movie_big_image);
        Picasso.with(getApplicationContext()).load(Constants.MOVIE_POSTER_PATH_BIG + movie.getBackdropPath())
                .placeholder(ContextCompat.getDrawable(getApplicationContext(), R.drawable.movie)).error(ContextCompat.getDrawable(getApplicationContext(), R.drawable.movie)).into(big_poster);

        TextView titleTV = (TextView) findViewById(R.id.title);
        titleTV.setText(movie.getTitle());


        TextView releaseDateTV = (TextView) findViewById(R.id.release_date);
        releaseDateTV.setText(movie.getReleaseDate());

        TextView voteAvgTV = (TextView) findViewById(R.id.vote_count);
        voteAvgTV.setText(movie.getVoteCount());


        TextView plotTV = (TextView) findViewById(R.id.plot);
        String noOverView = getString(R.string.no_overview);
        if(!TextUtils.isEmpty(movie.getOverview()))
        {
            noOverView = movie.getOverview();
        }
        plotTV.setText(noOverView);


    }

}
