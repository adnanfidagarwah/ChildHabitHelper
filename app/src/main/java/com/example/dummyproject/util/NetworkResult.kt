package com.example.dummyproject.util

sealed class NetworkResult<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(Status.SUCCESS, data)
    class Error<T>(message: String?, data: T? = null) :
        NetworkResult<T>(Status.LOADING, data, message)

    class Loading<T> : NetworkResult<T>(Status.ERROR)

}