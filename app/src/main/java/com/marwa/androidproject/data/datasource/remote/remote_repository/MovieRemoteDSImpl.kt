package com.marwa.androidproject.data.datasource.remote.remote_repository

import com.marwa.androidproject.data.datasource.remote.api.ApiProvider
import com.marwa.androidproject.data.datasource.remote.api.ApiService
import com.marwa.androidproject.data.datasource.remote.api.NetworkResource
import com.marwa.androidproject.data.datasource.remote.api.apiKey
import com.marwa.androidproject.data.datasource.remote.interfaces.MovieRemoteDS
import com.marwa.androidproject.data.model.MovieResponse
import com.marwa.androidproject.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRemoteDSImpl(private val apiService: ApiService) : MovieRemoteDS, ApiProvider() {
    override suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>> =
        apiRequest { apiService.fetchNowShowingMovies(apiKey) }


    override suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>> =
        apiRequest { apiService.fetchPopularMovies(apiKey) }

}