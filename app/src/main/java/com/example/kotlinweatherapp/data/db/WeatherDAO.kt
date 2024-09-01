package com.example.kotlinweatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinweatherapp.data.entites.WeatherEntity

@Dao
interface WeatherDAO {
    @Insert
    fun insertItem(weather: WeatherEntity)

    @Delete
    fun deleteItem(weatherItem : WeatherEntity)

    @Query("SELECT * FROM WeatherEntity")
    fun getAllItems(): LiveData<MutableList<WeatherEntity>>
}
