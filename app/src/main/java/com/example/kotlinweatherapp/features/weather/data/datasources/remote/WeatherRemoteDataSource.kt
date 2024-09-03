package com.example.kotlinweatherapp.features.weather.data.datasources.remote

import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


interface WeatherRemoteDataSource {
    suspend fun getWeatherItem(city:String):WeatherEntity?
}