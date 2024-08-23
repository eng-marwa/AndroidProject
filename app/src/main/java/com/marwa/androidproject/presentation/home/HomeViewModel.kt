package com.marwa.androidproject.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.androidproject.base.ViewState
import com.marwa.androidproject.data.datasource.remote.api.NetworkState
import com.marwa.androidproject.data.model.MovieResponse
import com.marwa.androidproject.domain.usecases.GetNowShowingUseCase
import com.marwa.androidproject.domain.usecases.GetPopularUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNowShowingUseCase: GetNowShowingUseCase,
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {
    private  val TAG = "HomeViewModel"
    private val _showingNowMoviesStatus =
        MutableStateFlow<ViewState<MovieResponse>>(ViewState.Empty())

    val showingNowMoves : StateFlow<ViewState<MovieResponse>> = _showingNowMoviesStatus

    fun getNowShowingMovies() {
        viewModelScope.launch {
            getNowShowingUseCase.getNowShowingMovies().collect {
                val state:ViewState<MovieResponse> = when (it.state) {
                    is NetworkState.Success -> ViewState.Loaded(it.data!!)
                    is NetworkState.Failure -> ViewState.Error(it.error!!)
                    else -> ViewState.Empty()
                }
                Log.d(TAG, "getNowShowingMovies: ${it.data}")
                _showingNowMoviesStatus.value = state
            }
        }
    }


    private val _popularMoviesStatus =
        MutableStateFlow<ViewState<MovieResponse>>(ViewState.Empty())

    val popularMovies : StateFlow<ViewState<MovieResponse>> = _popularMoviesStatus
    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularUseCase.getPopularMovies().collect {
                val state:ViewState<MovieResponse> = when (it.state) {
                    is NetworkState.Success -> ViewState.Loaded(it.data!!)
                    is NetworkState.Failure -> ViewState.Error(it.error!!)
                    else -> ViewState.Empty()
                }
                Log.d(TAG, "getPopularMovies: ${it.data}")
                _popularMoviesStatus.value = state
            }
        }
    }
}