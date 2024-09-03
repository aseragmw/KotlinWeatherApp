package com.example.kotlinweatherapp.core

sealed class RequestState <out T>  {
    data class Success<T>(val data: T) : RequestState<T>()
    data class Failure<T>(val error: String) : RequestState<T>()
    object Loading : RequestState<Nothing>()
    object Initial : RequestState<Nothing>()

}