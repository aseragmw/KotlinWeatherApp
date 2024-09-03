package com.example.kotlinweatherapp.features.weather.domain.repos

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


interface WeatherRepo {
    suspend fun getWeatherItem(city:String):WeatherEntity?
    fun deleteWeatherItem(weatherItem: WeatherEntity)
    fun getAllWeatherItems():LiveData<MutableList<WeatherEntity>>
    fun updateAllItems():Unit


}