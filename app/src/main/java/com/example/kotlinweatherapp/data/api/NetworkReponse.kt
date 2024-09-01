package com.example.kotlinweatherapp.data.api

sealed class NetworkReponse <out T> {
    data class Success<out T>(val data:T): NetworkReponse<T>()
    data class Failure(var message :String):NetworkReponse<Nothing>()
    object Loading: NetworkReponse<Nothing>()
}