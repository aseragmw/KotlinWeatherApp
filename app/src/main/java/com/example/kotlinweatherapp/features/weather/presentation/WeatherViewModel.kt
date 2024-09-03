package com.example.kotlinweatherapp.features.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinweatherapp.core.RequestState
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.usecases.DeleteWeatherItemUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetAllWeatherItemsUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetWeatherItemUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.UpdateAllItemsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val getWeatherItemUsecase: GetWeatherItemUsecase,
    val deleteWeatherItemUsecase: DeleteWeatherItemUsecase,
    val getAllWeatherItemsUsecase: GetAllWeatherItemsUsecase,
    val updateAllItemsUsecase: UpdateAllItemsUsecase
) : ViewModel() {

    private var _getWeatherItemState : MutableStateFlow<RequestState<WeatherEntity?>> = MutableStateFlow(RequestState.Initial)
    val getWeatherItemState : StateFlow<RequestState<WeatherEntity?>> = _getWeatherItemState
    fun getWeatherItem(city: String) {
        viewModelScope.launch {
            getWeatherItemUsecase.execute(city).collect{
                _getWeatherItemState.value = it
            }
        }
    }


    fun deleteItem(item: WeatherEntity) {
        deleteWeatherItemUsecase.execute(item)
    }

    fun getAllWeatherItems(): LiveData<MutableList<WeatherEntity>> {
        return getAllWeatherItemsUsecase.execute()
    }

    fun updateAllItems(){
        return updateAllItemsUsecase.invoke()
    }
}