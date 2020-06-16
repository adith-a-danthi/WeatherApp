package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.local.entity.WeatherLocationLocal
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import com.example.weatherapp.util.runInBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    private val executorService = Executors.newSingleThreadExecutor()
    val weatherData: MutableLiveData<WeatherLocation> = MutableLiveData()

    fun getWeatherData(lat: Float, lon: Float): LiveData<WeatherLocation> {

        val count = executorService.submit(Callable { localDataSource.getCount() }).get()

        val recentWeatherData: WeatherLocationLocal
        val diff: Long
        if (count > 0) {
            recentWeatherData =
                executorService.submit(Callable { localDataSource.getRecent() }).get()
            diff = (System.currentTimeMillis() - recentWeatherData.time) / 60000

            if (diff < 90) {
                weatherData.value = recentWeatherData.weatherLocationResponse
            } else {
//                runInBackground { localDataSource.delete(recentWeatherData) }
                makeApiCall(lat, lon)
            }
        } else {
            makeApiCall(lat, lon)
        }
        return weatherData
    }

    private fun makeApiCall(lat: Float, lon: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = remoteDataSource.openWeatherMapApi.getWeatherLatLon(lat, lon)
            localDataSource.insert(WeatherLocationLocal(0, System.currentTimeMillis(), data))
        }
    }
}