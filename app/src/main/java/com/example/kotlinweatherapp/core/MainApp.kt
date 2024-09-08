package com.example.kotlinweatherapp.core

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.room.Room
import com.example.kotlinweatherapp.core.db.WeatherDAO
import com.example.kotlinweatherapp.core.db.WeatherDB
import com.example.kotlinweatherapp.core.services.NotificationService.Companion.channelID
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                channelID,
                "Weather Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }

    }
}