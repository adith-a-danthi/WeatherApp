package com.example.weatherapp.data.source.remote.api.response.inner

/*

"main": {
    "temp": 281.52,
    "feels_like": 278.99,
    "temp_min": 280.15,
    "temp_max": 283.71,
    "pressure": 1016,
    "humidity": 93
  },

 */

data class Main(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Long,
    val humidity: Long
)