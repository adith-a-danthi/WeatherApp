package com.example.weatherapp.data.source.local.entity.convertor

import androidx.room.TypeConverter
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class WeatherLocationConvertor {

    companion object{
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<WeatherLocation> = moshi.adapter(WeatherLocation::class.java)

        @JvmStatic
        @TypeConverter
        fun weatherlocationToString (weatherLocation: WeatherLocation): String {
            return adapter.toJson(weatherLocation)
        }

        @JvmStatic
        @TypeConverter
        fun stringToWeatherLocation (json: String): WeatherLocation {
            return adapter.fromJson(json)!!
        }
    }

}