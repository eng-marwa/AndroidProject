package com.marwa.androidproject.data.datasource.remote.api

import com.marwa.androidproject.base.BaseException
import com.marwa.androidproject.utils.toApiErrorBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class ApiProvider {
    fun <T> apiRequest(apiCall: suspend () -> Response<T>): Flow<NetworkResource<T>> {
        return flow {
            emit(NetworkResource(state = NetworkState.Loading))
            val response = apiCall()//calling anonymous
            val networkResource = if (response.isSuccessful && response.code() == 200) {
                NetworkResource(state = NetworkState.Success, data = response.body())
            } else {
                try {
                    NetworkResource(
                        state = NetworkState.Failure,
                        error = BaseException(
                            statusCode = response.code(),
                            statusMessage = response.errorBody()?.toApiErrorBy()?.message ?: "",
                            success = false
                        )
                    )
                } catch (ex: Exception) {
                    NetworkResource(
                        state = NetworkState.Failure,
                        error = BaseException(
                            statusCode = response.code(),
                            statusMessage = ex.message ?: "",
                            success = false
                        )
                    )
                }
            }
            emit(networkResource)
        }.flowOn(Dispatchers.IO)
    }
}