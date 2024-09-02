package com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val location: String,
    val temp: String,
    val condition: String,
    val iconPath: String,
)