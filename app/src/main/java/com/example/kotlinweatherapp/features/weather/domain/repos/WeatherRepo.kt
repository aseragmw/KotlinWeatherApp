package com.example.kotlinweatherapp.features.weather.domain.repos

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


interface WeatherRepo {
    fun getWeatherItem(city:String,onFailure:()->Unit)
    fun deleteWeatherItem(weatherItem: WeatherEntity)
    fun getAllWeatherItems():LiveData<MutableList<WeatherEntity>>
}