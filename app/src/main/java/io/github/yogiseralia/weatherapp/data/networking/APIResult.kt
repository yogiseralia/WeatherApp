package io.github.yogiseralia.weatherapp.data.networking

sealed class APIResult<out T> {
    data class Loading(val isLoading: Boolean) : APIResult<Nothing>()
    data class Success<out T>(val data: T) : APIResult<T>()
    data class Error(val message: String, val cause: Exception? = null) : APIResult<Nothing>()
}