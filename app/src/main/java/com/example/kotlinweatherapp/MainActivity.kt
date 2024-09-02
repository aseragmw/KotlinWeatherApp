package com.example.kotlinweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinweatherapp.features.weather.presentation.screens.HomeScreen
import com.example.kotlinweatherapp.features.weather.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel = ViewModelProvider(this)[WeatherViewModel::class.java]
            HomeScreen(viewmodel,applicationContext)
        }
    }
}

