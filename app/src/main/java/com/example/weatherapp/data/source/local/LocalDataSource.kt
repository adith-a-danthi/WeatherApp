package com.example.weatherapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.source.local.db.WeatherLocationDatabase
import com.example.weatherapp.data.source.local.entity.WeatherLocationLocal
import com.example.weatherapp.util.runInBackground
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class LocalDataSource @Inject constructor(val locationDatabase: WeatherLocationDatabase) {

    fun insert(weatherLocationLocal: WeatherLocationLocal) {
        runInBackground {
            locationDatabase.weatherLocationDao().insert(weatherLocationLocal)
        }
    }

    // TODO("Add other operations")
    fun delete(weatherLocationLocal: WeatherLocationLocal) {
        runInBackground {
            locationDatabase.weatherLocationDao().delete(weatherLocationLocal)
        }
    }

    fun getRecent(): WeatherLocationLocal {
        return locationDatabase.weatherLocationDao().getRecentData()
    }

    fun getCount(): Long {
        return locationDatabase.weatherLocationDao().getCount()
    }

}