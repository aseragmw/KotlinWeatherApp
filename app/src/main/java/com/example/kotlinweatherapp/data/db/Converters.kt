package com.example.kotlinweatherapp.data.db

import androidx.room.TypeConverter
import com.example.kotlinweatherapp.data.entites.Condition
import com.example.kotlinweatherapp.data.entites.Current
import com.example.kotlinweatherapp.data.entites.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    val gson = Gson()

    @TypeConverter
    fun fromCurrent(current: Current): String {
        return gson.toJson(current)
    }
    @TypeConverter
    fun toCurrent(json:String):Current{
        val type = object: TypeToken<Current>(){}.type
        return gson.fromJson(json,type)
    }
    @TypeConverter
    fun fromCondition(condition: Condition): String {
        return gson.toJson(condition)
    }
    @TypeConverter
    fun toCondition(json:String):Condition{
        val type = object: TypeToken<Condition>(){}.type
        return gson.fromJson(json,type)
    }
    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }
    @TypeConverter
    fun toLocation(json:String):Location{
        val type = object: TypeToken<Location>(){}.type
        return gson.fromJson(json,type)
    }
}