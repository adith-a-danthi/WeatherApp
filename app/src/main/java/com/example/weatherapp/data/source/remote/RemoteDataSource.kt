package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.source.DataSource
import com.example.weatherapp.data.source.remote.api.OpenWeatherMapApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val openWeatherMapApi: OpenWeatherMapApi) : DataSource{


    override suspend fun getWeatherByLocation(latitude: Float, longitude: Float) = openWeatherMapApi.getWeatherLatLon(latitude,longitude)


}