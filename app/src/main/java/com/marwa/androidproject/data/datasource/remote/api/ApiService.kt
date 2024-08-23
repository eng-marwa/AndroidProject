package com.marwa.androidproject.data.datasource.remote.api

import com.marwa.androidproject.data.model.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        fun createService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }

    @GET(nowShowingEndPoint)
    suspend fun fetchNowShowingMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET(popularEndPoint)
    suspend fun fetchPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
}