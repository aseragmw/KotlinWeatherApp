package com.example.kotlinweatherapp.data.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val current: Current,
    val location: Location
)