package com.example.weatherapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.data.source.local.entity.convertor.WeatherLocationConvertor
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation

@Entity(tableName = "weather_data")
data class WeatherLocationLocal (

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    var time: Long,

    // Converts string to WeatherLocation and vice - versa
    @TypeConverters(value = [WeatherLocationConvertor::class])
    var weatherLocationResponse: WeatherLocation

)