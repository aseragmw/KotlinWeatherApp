package com.example.kotlinweatherapp.features.weather.data.datasources.local

import android.provider.Settings.Global
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.core.MainApp
import com.example.kotlinweatherapp.core.db.WeatherDAO
import com.example.kotlinweatherapp.core.services.NotificationService
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDAO,
    private val notificationService: NotificationService
) : WeatherLocalDataSource {
    override suspend fun insertWeatherItem(weatherItem: WeatherEntity) {
        CoroutineScope(
            Dispatchers.IO
        ).launch(
        ) {
            val storedItems = weatherDao.getAllItemsAsList()
            val itemExists = storedItems.find { it.location == weatherItem.location }
            if (itemExists != null) {
                weatherDao.upsertItem(weatherItem)
            } else {
                weatherDao.insertItem(weatherItem)
                notificationService.showNotification("${weatherItem.location} added")
            }
        }
    }

    override suspend fun deleteWeatherItem(weatherItem: WeatherEntity) {
        CoroutineScope(
            Dispatchers.IO
        ).launch(
        ) {
            weatherDao.deleteItem(weatherItem)
            notificationService.showNotification( "${weatherItem.location} deleted")
        }
    }

    override fun getAllWeatherItems(): LiveData<MutableList<WeatherEntity>> {
        return weatherDao.getAllItems()
    }

    override fun getAllWeatherItemsAsList(): MutableList<WeatherEntity> {
        return weatherDao.getAllItemsAsList()
    }
}