package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.DataRepository
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel()
{

    val apiData = MutableLiveData<WeatherLocation>()

    fun getWeather(latitude: Float, longitude: Float) {
        apiData.postValue(dataRepository.getWeatherData(latitude, longitude).value)
    }

}