package io.github.yogiseralia.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.yogiseralia.weatherapp.BuildConfig
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.data.db.AppDatabase


class MainActivity : AppCompatActivity() {

    private val navController: NavController?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()

    private val bottomNavigationView: BottomNavigationView
        get() = findViewById(R.id.bottom_navigation)

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
        navController?.let { bottomNavigationView.setupWithNavController(it) }
    }

    private fun setupViewModel() {
        /*no-op*/
    }
}