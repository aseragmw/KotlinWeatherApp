package com.example.kotlinweatherapp.features.weather.domain.usecases

import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import javax.inject.Inject

class UpdateAllItemsUsecase @Inject constructor(
    val weatherRepo:WeatherRepo
) {
     operator fun invoke(){
        weatherRepo.updateAllItems()
    }
}