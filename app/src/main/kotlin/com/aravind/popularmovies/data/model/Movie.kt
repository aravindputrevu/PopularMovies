package com.aravind.popularmovies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<Movie>
)

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("release_date") val releaseDate: String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
) {
    val posterUrl: String get() = "https://image.tmdb.org/t/p/w342$posterPath"
    val backdropUrl: String get() = "https://image.tmdb.org/t/p/w780$backdropPath"
}
