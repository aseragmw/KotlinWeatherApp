package com.example.kotlinweatherapp.core.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import com.example.kotlinweatherapp.core.services.WeatherSyncService

class ConnectivityBdReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if(intent.action=="android.net.conn.CONNECTIVITY_CHANGE"){
                val isConnected = isNetworkAvailable(context)
                if (isConnected){
                    Toast.makeText(context,"Connected to internet",Toast.LENGTH_SHORT).show()
                    context?.let{
                        val startIntent = Intent(it, WeatherSyncService::class.java)
                        it.startService(startIntent)
                        Toast.makeText(it,"Sync Service Started",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(context,"Disconnected from internet",Toast.LENGTH_SHORT).show()
                    context?.let{
                        val stopIntent = Intent(it, WeatherSyncService::class.java)
                        it.stopService(stopIntent)
                        Toast.makeText(it,"Sync Service Stopped",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        context?.let {
            val manager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = manager.activeNetwork
            val capabilities = manager.getNetworkCapabilities(network)
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        }
        return false
    }
}