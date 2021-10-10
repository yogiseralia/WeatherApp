package io.github.yogiseralia.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.yogiseralia.weatherapp.BuildConfig
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.data.db.AppDatabase


class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() {
            return findNavController(R.id.nav_host_fragment)
        }

    private val bottomNavigationView: BottomNavigationView
        get() {
            return findViewById(R.id.bottom_navigation)
        }
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDB()
        setupBottomNav()
        setupViewModel()
    }

    private fun setupDB() {
        if (BuildConfig.DEBUG) {
            (application as WeatherApp).appDatabase =
                Room.inMemoryDatabaseBuilder(applicationContext, AppDatabase::class.java).build()
        } else {
            (application as WeatherApp).appDatabase =
                Room.databaseBuilder(applicationContext, AppDatabase::class.java, "weather-db")
                    .build()
        }
    }

    private fun setupBottomNav() {
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupViewModel() {

    }
}