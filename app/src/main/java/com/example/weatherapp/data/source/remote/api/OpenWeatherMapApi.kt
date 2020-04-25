package com.example.weatherapp.data.source.remote.api

import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    // api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}
    @GET(Urls.WEATHER_ENDPOINT)
    suspend fun getWeatherLatLon(
        @Query(Urls.URL_PARAM_LAT) latitude: Float,
        @Query(Urls.URL_PARAM_LON) longitude: Float,
        @Query(Urls.URL_PARAM_APP_ID) appID: String = Urls.API_KEY
    ): WeatherLocation

    @GET(Urls.WEATHER_ENDPOINT)
    fun getWeatherLatLonTest(
        @Query(Urls.URL_PARAM_LAT) latitude: Float,
        @Query(Urls.URL_PARAM_LON) longitude: Float,
        @Query(Urls.URL_PARAM_APP_ID) appID: String = Urls.API_KEY
    ): Call<WeatherLocation>

}