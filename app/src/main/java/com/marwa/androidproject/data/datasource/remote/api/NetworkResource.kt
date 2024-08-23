package com.marwa.androidproject.data.datasource.remote.api

import com.marwa.androidproject.base.BaseException

open class NetworkResource<T>(
    val state: NetworkState,
    val data: T? = null,
    val error: BaseException? = null
) {
    class Loading() : NetworkResource<Boolean>(NetworkState.Loading)
    class Success<T>(data: T) : NetworkResource<T>(NetworkState.Success, data)
    class Failure(error: BaseException) :
        NetworkResource<BaseException>(NetworkState.Failure, error)
}