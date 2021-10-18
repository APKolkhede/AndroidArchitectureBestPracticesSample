package com.techtest.core.network

sealed class NetworkState<out T> {
    data class Success<out R>(val value: R?) : NetworkState<R>()

    data class Failure(
        val error:
            String
    ) : NetworkState<Nothing>()

    object Loading : NetworkState<Nothing>()
}
