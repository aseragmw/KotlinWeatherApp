package com.example.kotlinweatherapp.core.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class WeatherDBBuilder @Inject constructor(
    private val context: Context
) {
    private var _db:WeatherDB? = null;
    fun getInstance():WeatherDB{
        synchronized(this){
            if(_db == null){
                _db =  Room.databaseBuilder(
                    context,
                    WeatherDB::class.java,
                    "weather.db"
                ).fallbackToDestructiveMigration().build();
            }
        }
        return _db!!;
    }
}