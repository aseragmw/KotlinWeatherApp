package com.example.kotlinweatherapp.features.weather.domain.usecases

import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import javax.inject.Inject

class GetAllWeatherItemsUsecase @Inject constructor(
    val weatherRepository: WeatherRepo
) {
    fun execute () = weatherRepository.getAllWeatherItems()
}