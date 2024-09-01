package com.example.kotlinweatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinweatherapp.data.entites.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class WeatherDB : RoomDatabase() {
    companion object{
        val DATABASE_NAME = "weather_db"
    }
    abstract fun getDao(): WeatherDAO
}