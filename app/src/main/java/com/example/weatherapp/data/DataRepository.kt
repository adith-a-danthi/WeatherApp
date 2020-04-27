package com.example.weatherapp.data

import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.remote.api.OpenWeatherMapApi
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val openWeatherMapApi: OpenWeatherMapApi
)  {
    fun dummy() = 3

    fun testAPI(lat: Float, lon: Float) = openWeatherMapApi.getWeatherLatLonTest(lat, lon)
}