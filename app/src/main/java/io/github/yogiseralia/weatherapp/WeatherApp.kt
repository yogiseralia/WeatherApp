package io.github.yogiseralia.weatherapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import androidx.room.Room
import io.github.yogiseralia.weatherapp.data.db.AppDatabase

/**
 * Please refer to designs -
 * https://www.behance.net/gallery/90366995/Weather-App
 */
class WeatherApp : MultiDexApplication() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        setupDB()
    }

    private fun setupDB() {
        if (BuildConfig.DEBUG) {
            appDatabase = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java).build()
        } else {
            appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "weather-db").build()
        }
    }
}