package com.example.kotlinweatherapp.core.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.kotlinweatherapp.MainActivity
import com.example.kotlinweatherapp.R
import com.example.kotlinweatherapp.core.MainApp
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class WeatherSyncService  : Service() {

    @Inject
    lateinit var weatherRepo: WeatherRepo

    val serviceJob = Job()

     val channelID = "WeatherSyncServiceChannel"
    @Inject
    lateinit var notificationService: NotificationService

    override fun onCreate() {
        super.onCreate()
        Log.d("WeatherSyncService", "onCreate")
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(
                channelID,
                "Weather Sync Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentText("Syncing weather data...")
            .setContentTitle("Weather Sync Service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(
                R.drawable.baseline_add_24,
                "Open",
                pendingIntent
            )
            .build()

        startForeground(1, notification)

        CoroutineScope(
            Dispatchers.IO + serviceJob
        ).launch {
            while (true){
                Log.d("WeatherSyncService", "updating all items...")
                weatherRepo.updateAllItems()
                delay(10000L)
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d("WeatherSyncService", "onDestroy")
    }

}