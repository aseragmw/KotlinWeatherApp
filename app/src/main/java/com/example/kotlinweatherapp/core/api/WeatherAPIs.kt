package com.example.kotlinweatherapp.core.api

import com.example.kotlinweatherapp.features.weather.data.models.WeatherModel
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIs {
    @GET("/v1/current.json")
    suspend fun getWeatherItem(
        @Query("key") key: String = "ee178d27113d4dd1a7e193657243108",
        @Query("q") city: String
    ):Response<WeatherModel>

}