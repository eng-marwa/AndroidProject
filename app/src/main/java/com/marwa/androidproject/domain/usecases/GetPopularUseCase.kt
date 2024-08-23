package com.marwa.androidproject.domain.usecases

import com.marwa.androidproject.data.datasource.remote.api.NetworkResource
import com.marwa.androidproject.data.model.MovieResponse
import com.marwa.androidproject.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularUseCase(private val movieRepository: MovieRepository) {
    suspend fun getPopularMovies(): Flow<NetworkResource<MovieResponse>>  = movieRepository.getPopularMovies()
}