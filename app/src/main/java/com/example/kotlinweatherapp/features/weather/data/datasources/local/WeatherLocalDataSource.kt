package com.example.kotlinweatherapp.features.weather.data.datasources.local

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


interface WeatherLocalDataSource {
    suspend fun insertWeatherItem(weatherItem:WeatherEntity)
    suspend fun deleteWeatherItem(weatherItem: WeatherEntity)
    fun getAllWeatherItems(): LiveData<MutableList<WeatherEntity>>
    fun getAllWeatherItemsAsList(): MutableList<WeatherEntity>

}