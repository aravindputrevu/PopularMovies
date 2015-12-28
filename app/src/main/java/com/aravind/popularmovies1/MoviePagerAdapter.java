package com.aravind.popularmovies1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MoviePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MoviePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PopularMoviesFragment tab1 = new PopularMoviesFragment();
                return tab1;
            case 1:
                RatingMovieFragment tab2 = new RatingMovieFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

