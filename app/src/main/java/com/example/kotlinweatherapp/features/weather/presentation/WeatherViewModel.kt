package com.example.kotlinweatherapp.features.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.usecases.DeleteWeatherItemUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetAllWeatherItemsUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetWeatherItemUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val getWeatherItemUsecase : GetWeatherItemUsecase,
    val deleteWeatherItemUsecase: DeleteWeatherItemUsecase,
    val getAllWeatherItemsUsecase: GetAllWeatherItemsUsecase
) : ViewModel() {
    fun getWeatherItem(city: String, onFailure: () -> Unit) {
        getWeatherItemUsecase.execute(city,onFailure)
    }

    fun deleteItem(item: WeatherEntity) {
       deleteWeatherItemUsecase.execute(item)
    }

    fun getAllWeatherItems(): LiveData<MutableList<WeatherEntity>> {
        return getAllWeatherItemsUsecase.execute()
    }
}