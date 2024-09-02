package com.example.kotlinweatherapp.core

import android.app.Application
import androidx.room.Room
import com.example.kotlinweatherapp.core.db.WeatherDAO
import com.example.kotlinweatherapp.core.db.WeatherDB
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application()