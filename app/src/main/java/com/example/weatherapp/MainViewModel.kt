package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.DataRepository
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel()
{

    val apiData = MutableLiveData<WeatherLocation>()

    fun getWeather(latitude: Float, longitude: Float) {
        apiData.value = dataRepository.getWeatherData(latitude, longitude).value
    }

}