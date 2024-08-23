package com.marwa.androidproject.domain.repository

import com.marwa.androidproject.data.datasource.remote.api.NetworkResource
import com.marwa.androidproject.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowShowingMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getPopularMovies():Flow<NetworkResource<MovieResponse>>
}