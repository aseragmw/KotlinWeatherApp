package com.example.kotlinweatherapp.features.weather.data.datasources.remote

import android.util.Log
import com.example.kotlinweatherapp.core.api.WeatherRetrofitBuilder
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRemoteDataSourceImpl : WeatherRemoteDataSource {
    override suspend fun getWeatherItem(city: String,onFailure: () -> Unit):WeatherEntity?{
        return try {
            var response = WeatherRetrofitBuilder.WeatherAPI.getWeatherItem(city = city)
            if (response.isSuccessful) {
                response.body()?.asEntity()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}