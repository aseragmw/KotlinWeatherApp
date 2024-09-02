package com.example.kotlinweatherapp.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitBuilder {
    private const val BASE_URL = "https://api.weatherapi.com"
    private fun getInstance(): Retrofit {
         return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    val WeatherAPI = getInstance().create(WeatherAPIs::class.java)
}