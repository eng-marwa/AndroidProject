package com.marwa.androidproject.data.datasource.remote.api

sealed class NetworkState {
    data object Loading : NetworkState()
    data object Success : NetworkState()
    data object Failure : NetworkState()
    data object Idle : NetworkState()
}