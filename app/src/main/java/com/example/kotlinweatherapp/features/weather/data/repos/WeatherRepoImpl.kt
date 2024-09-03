package com.example.kotlinweatherapp.features.weather.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.kotlinweatherapp.features.weather.data.datasources.local.WeatherLocalDataSource
import com.example.kotlinweatherapp.features.weather.data.datasources.remote.WeatherRemoteDataSource
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    val weatherRemoteDataSource: WeatherRemoteDataSource,
    val weatherLocalDataSource: WeatherLocalDataSource
) : WeatherRepo {
    suspend override fun getWeatherItem(city: String):WeatherEntity? {
        return withContext(
            Dispatchers.IO
        ) {

                val item = weatherRemoteDataSource.getWeatherItem(city)
                Log.d("item", item.toString())

                if (item == null) {
                    withContext(
                        Dispatchers.Main
                    ) {
                        throw Exception("Failed to get weather")
                    }
                } else {
                    weatherLocalDataSource.insertWeatherItem(item)
                }
                item

        }
    }

    override fun deleteWeatherItem(weatherItem: WeatherEntity) {
        CoroutineScope(
            Dispatchers.IO
        ).launch {
            weatherLocalDataSource.deleteWeatherItem(weatherItem)
        }
    }

    override fun getAllWeatherItems(): LiveData<MutableList<WeatherEntity>> {
        return weatherLocalDataSource.getAllWeatherItems()
    }


}