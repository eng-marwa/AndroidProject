package com.marwa.androidproject.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("results") val results: List<Result>)

data class Result(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("id") val id: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genre_ids") val genreIds: List<Int>
)
