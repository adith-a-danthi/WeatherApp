package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.data.source.local.db.WeatherLocationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(application: Application) = Room.databaseBuilder(
        application,
        WeatherLocationDatabase::class.java,
        "weather_db"
    ).build()

}