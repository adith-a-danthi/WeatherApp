package com.example.weatherapp.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.source.local.entity.WeatherLocationLocal
import com.example.weatherapp.data.source.local.entity.convertor.WeatherLocationConvertor

@Database(entities = [WeatherLocationLocal::class], version = 1, exportSchema = false)
@TypeConverters(WeatherLocationConvertor::class)
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