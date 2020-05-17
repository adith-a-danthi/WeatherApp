package com.example.weatherapp.data.source.local

import com.example.weatherapp.data.source.local.db.WeatherLocationDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(locationDatabase: WeatherLocationDatabase)