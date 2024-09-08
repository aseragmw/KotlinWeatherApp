package com.example.kotlinweatherapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinweatherapp.core.broadcast_receivers.ConnectivityBdReciever
import com.example.kotlinweatherapp.core.services.WeatherSyncService
import com.example.kotlinweatherapp.features.weather.presentation.screens.HomeScreen
import com.example.kotlinweatherapp.features.weather.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val filter  = IntentFilter(
            "android.net.conn.CONNECTIVITY_CHANGE"
        )
        registerReceiver(
            ConnectivityBdReciever(),
            filter
        )
        val intent = Intent(this, WeatherSyncService::class.java)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)

        }
        val networkManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = networkManager.activeNetwork
        val capabilities = networkManager.getNetworkCapabilities(network)
        val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        if(isConnected){
            startService(intent)
        }
        else{
            Toast.makeText(this,"Please Enable Internet To Sync", Toast.LENGTH_SHORT).show()
        }

        setContent {
            val viewmodel = ViewModelProvider(this)[WeatherViewModel::class.java]
            HomeScreen(viewmodel,applicationContext)
        }
    }
}

