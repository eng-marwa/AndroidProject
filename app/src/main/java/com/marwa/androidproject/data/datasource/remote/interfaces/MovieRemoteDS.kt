package com.marwa.androidproject.data.datasource.remote.interfaces

import com.marwa.androidproject.data.datasource.remote.api.NetworkResource
import com.marwa.androidproject.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDS {
    suspend fun getRemoteNowShowingMovies():  Flow<NetworkResource<MovieResponse>>
    suspend fun getRemotePopularMovies():  Flow<NetworkResource<MovieResponse>>
}