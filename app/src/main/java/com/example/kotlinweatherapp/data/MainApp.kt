package com.example.kotlinweatherapp.data

import android.app.Application
import androidx.room.Room
import com.example.kotlinweatherapp.data.db.WeatherDAO
import com.example.kotlinweatherapp.data.db.WeatherDB

class MainApp : Application() {
    companion object{
        lateinit var dao :WeatherDAO
    }
    override fun onCreate() {
        super.onCreate()
        val db= Room.databaseBuilder(
            applicationContext,
            WeatherDB::class.java,
            "weather_db"
        ).build()
        dao = db.getDao()
    }
}