package io.github.yogiseralia.weatherapp

import android.app.Application
import androidx.room.Room
import io.github.yogiseralia.weatherapp.db.AppDatabase

class WeatherApp : Application() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

//        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "weather-db").build()
        appDatabase = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java).build()
    }
}