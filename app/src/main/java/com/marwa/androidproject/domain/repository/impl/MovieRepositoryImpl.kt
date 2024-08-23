package com.marwa.androidproject.domain.repository.impl

import com.marwa.androidproject.data.datasource.remote.api.NetworkResource
import com.marwa.androidproject.data.datasource.remote.interfaces.MovieRemoteDS
import com.marwa.androidproject.data.model.MovieResponse
import com.marwa.androidproject.domain.repository.MovieRepository
import com.marwa.androidproject.domain.usecases.GetNowShowingUseCase
import com.marwa.androidproject.domain.usecases.GetPopularUseCase
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemoteDS: MovieRemoteDS

) : MovieRepository {
    override suspend fun getNowShowingMovies(): Flow<NetworkResource<MovieResponse>> =
        movieRemoteDS.getRemoteNowShowingMovies()


    override suspend fun getPopularMovies(): Flow<NetworkResource<MovieResponse>>  =
        movieRemoteDS.getRemotePopularMovies()

}