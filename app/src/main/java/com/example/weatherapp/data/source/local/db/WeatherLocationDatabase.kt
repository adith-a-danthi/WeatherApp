package com.example.weatherapp.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation

//@Database(entities = [WeatherLocation::class], version = 1)
abstract class WeatherLocationDatabase : RoomDatabase() {

    abstract fun weatherLocationDao(): WeatherLocationDao

    // Taken care by DatabaseModule
/*    companion object {
        @Volatile
        private var INSTANCE: WeatherLocationDatabase? = null

        internal fun getDatabase(context: Context): WeatherLocationDatabase? {
            if (INSTANCE == null) {
                synchronized(Database::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WeatherLocationDatabase::class.java,
                            "weather_db"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }*/

}