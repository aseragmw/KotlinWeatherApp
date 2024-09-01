package com.example.kotlinweatherapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinweatherapp.data.MainApp
import com.example.kotlinweatherapp.data.api.NetworkReponse
import com.example.kotlinweatherapp.data.api.WeatherRetrofitBuilder
import com.example.kotlinweatherapp.data.entites.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    var weatherData = MutableLiveData<MutableList<WeatherEntity>>()

    init {
        val items = MainApp.dao.getAllItems()
        items.observeForever {
            weatherData.value = it
        }

    }

    fun getWeatherItem(city: String, onFailure: () -> Unit) {
        viewModelScope.launch {
            try {
                var response = WeatherRetrofitBuilder.WeatherAPI.getWeatherItem(city = city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("WeatherViewModel", "getWeatherItem: $it")
                        viewModelScope.launch(
                            Dispatchers.IO
                        ) {
                            MainApp.dao.insertItem(it)
                        }
                    }
                } else {
                    onFailure()
                }
            } catch (e: Exception) {
                onFailure()
            }
        }
    }

    fun deleteItem(item: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            MainApp.dao.deleteItem(item)
        }
    }
}