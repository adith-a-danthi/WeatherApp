package com.example.weatherapp

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
    val value = dataRepository.dummy()

    val apiData = MutableLiveData<WeatherLocation>()

    fun getWeather(latitude: Float, longitude: Float) {
        val call = dataRepository.testAPI(latitude, longitude)
        call.enqueue(object: Callback<WeatherLocation>{
            override fun onFailure(call: Call<WeatherLocation>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<WeatherLocation>,
                response: Response<WeatherLocation>
            ) {
                apiData.postValue(response.body())
            }

        })
    }

}