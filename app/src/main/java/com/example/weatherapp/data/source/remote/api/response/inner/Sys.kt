package com.example.weatherapp.data.source.remote.api.response.inner
/*

"sys": {
    "type": 3,
    "id": 2019346,
    "message": 0.0065,
    "country": "JP",
    "sunrise": 1560281377,
    "sunset": 1560333478
  },

*/

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)