package com.aravind.popularmovies.data

import com.aravind.popularmovies.BuildConfig
import com.aravind.popularmovies.data.model.Movie
import com.aravind.popularmovies.data.remote.TmdbApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val api: TmdbApi
) {
    suspend fun getPopularMovies(page: Int = 1): List<Movie> =
        api.getMovies(
            sortBy = TmdbApi.SORT_POPULARITY,
            page = page,
            apiKey = BuildConfig.TMDB_API_KEY
        ).results

    suspend fun getTopRatedMovies(page: Int = 1): List<Movie> =
        api.getMovies(
            sortBy = TmdbApi.SORT_RATING,
            page = page,
            apiKey = BuildConfig.TMDB_API_KEY
        ).results
}
