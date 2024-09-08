package com.example.kotlinweatherapp.core.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import com.example.kotlinweatherapp.MainActivity
import com.example.kotlinweatherapp.R
import javax.inject.Inject

class NotificationService @Inject constructor(
    val context: Context
) {
    companion object {
        val channelID = "WeatherNotificationsChannel"
        var notificationID = 0
    }

    fun showNotification(content: String) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager =
            context.getSystemService(NotificationManager::class.java) as NotificationManager
        val notification = NotificationCompat.Builder(context, channelID)
            .setContentText(content)
            .setContentTitle("Weather Sync Service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(
                R.drawable.baseline_add_24,
                "Open",
                pendingIntent
            )
            .build()
        notificationManager.notify(++notificationID, notification)
    }

}