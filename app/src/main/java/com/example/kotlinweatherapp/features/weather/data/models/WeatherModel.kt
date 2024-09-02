package com.example.kotlinweatherapp.features.weather.data.models

import com.example.kotlinweatherapp.features.weather.domain.entities.weather_entity.WeatherEntity


data class WeatherModel(
    val current: Current,
    val location: Location
) {
    fun asEntity(): WeatherEntity {
        return WeatherEntity(
            id = 0,
            location = "${location.name ?: ""}, ${location.country ?: ""}",
            temp = "${current.temp_c ?: ""}°C",
            condition = current.condition.text ?: "",
            iconPath = "https:${current.condition.icon ?: ""}"
        )
    }
}