package com.example.kotlinweatherapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 5)
abstract class WeatherDB : RoomDatabase() {
    companion object{
        val DATABASE_NAME = "weather_db"
    }
    abstract fun getDao(): WeatherDAO
}