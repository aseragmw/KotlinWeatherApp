package com.example.kotlinweatherapp.features.weather.domain.usecases

import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import javax.inject.Inject

class GetWeatherItemUsecase @Inject constructor(
   val weatherRepository: WeatherRepo
){
    fun execute (city:String,onFailure:()->Unit) = weatherRepository.getWeatherItem(city,onFailure)
}