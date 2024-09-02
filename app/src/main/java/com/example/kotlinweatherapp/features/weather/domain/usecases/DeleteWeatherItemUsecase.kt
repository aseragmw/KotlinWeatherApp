package com.example.kotlinweatherapp.features.weather.domain.usecases

import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import javax.inject.Inject

class DeleteWeatherItemUsecase @Inject constructor (
    val weatherRepository: WeatherRepo
) {
    fun execute(weatherItem:WeatherEntity) = weatherRepository.deleteWeatherItem(weatherItem)
}