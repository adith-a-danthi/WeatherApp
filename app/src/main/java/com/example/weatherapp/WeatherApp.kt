package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

class WeatherApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(this)
    }

}