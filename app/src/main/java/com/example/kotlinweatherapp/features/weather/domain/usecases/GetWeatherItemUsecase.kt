package com.example.kotlinweatherapp.features.weather.domain.usecases

import android.util.Log
import com.example.kotlinweatherapp.core.RequestState
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherItemUsecase @Inject constructor(
   val weatherRepository: WeatherRepo
){
    fun execute (city:String) : Flow<RequestState<WeatherEntity?>> {
       return flow {
            try {
                emit(RequestState.Loading)
                emit(RequestState.Success(weatherRepository.getWeatherItem(city)))
            }
            catch (e:Exception){
                emit(RequestState.Failure(error = e.message!!))
            }
        }
    }
}