package com.aravind.popularmovies.data.remote

import com.aravind.popularmovies.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String
    ): MovieResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val SORT_POPULARITY = "popularity.desc"
        const val SORT_RATING = "vote_average.desc"
    }
}
