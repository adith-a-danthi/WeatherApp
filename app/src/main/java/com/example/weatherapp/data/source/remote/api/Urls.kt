package com.example.weatherapp.data.source.remote.api

import com.example.weatherapp.BuildConfig

object Urls {

    const val API_KEY = BuildConfig.API_KEY

    private const val PROTOCOL = "https://"
    private const val DOMAIN = "api.openweathermap.org"
    private const val PATH = "data"
    private const val API_VERSION = "2.5"

    const val BASE_URL =  "$PROTOCOL$DOMAIN/$PATH/$API_VERSION/"

    const val WEATHER_ENDPOINT = "weather"

    const val URL_PARAM_APP_ID = "appid"
    const val URL_PARAM_LAT = "lat"
    const val URL_PARAM_LON = "lon"

}