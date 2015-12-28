package com.aravind.popularmovies1;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie>{

    private static final String LOG = MovieAdapter.class.getSimpleName();


    public MovieAdapter(Activity context, List<Movie> movieList) {
        super(context, 0, movieList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_tile, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.with(this.getContext()).load(Constants.MOVIE_POSTER_PATH_SMALL + movie.getPosterPath()).placeholder(ContextCompat.getDrawable(this.getContext(), R.drawable.movie)).error(ContextCompat.getDrawable(this.getContext(), R.drawable.movie)).into(imageView);



        return convertView;
    }

}
