package com.example.kotlinweatherapp.core.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


@Dao
interface WeatherDAO {
    @Insert
    fun insertItem(weather: WeatherEntity)

    @Upsert
    fun upsertItem(weather: WeatherEntity)

    @Delete
    fun deleteItem(weatherItem : WeatherEntity)

    @Query("SELECT * FROM WeatherEntity")
    fun getAllItems(): LiveData<MutableList<WeatherEntity>>
    @Query("SELECT * FROM WeatherEntity")
    fun getAllItemsAsList(): MutableList<WeatherEntity>
}
