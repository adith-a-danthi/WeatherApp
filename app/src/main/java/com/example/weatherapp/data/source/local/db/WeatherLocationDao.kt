package com.example.weatherapp.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.source.local.entity.WeatherLocationLocal
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation

@Dao
interface WeatherLocationDao {

    @Insert
    fun insert(weatherLocation: WeatherLocationLocal)

    @Query("SELECT * FROM weather_data ORDER BY id DESC LIMIT 1")
    fun getRecentData(): LiveData<WeatherLocationLocal>

    @Delete
    fun delete(weatherLocation: WeatherLocationLocal)
}