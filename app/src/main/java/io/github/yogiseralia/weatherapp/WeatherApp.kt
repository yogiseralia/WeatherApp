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
}