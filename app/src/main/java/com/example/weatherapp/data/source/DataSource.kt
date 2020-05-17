package com.example.weatherapp.data.source

import com.example.weatherapp.data.source.remote.api.response.WeatherLocation

interface DataSource {

    suspend fun getWeatherByLocation(latitude: Float, longitude: Float): WeatherLocation

}