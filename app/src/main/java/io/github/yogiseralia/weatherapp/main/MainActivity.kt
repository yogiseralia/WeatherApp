package io.github.yogiseralia.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.data.networking.APIService
import io.github.yogiseralia.weatherapp.data.networking.WeatherAPIToDBMapper
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

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
        setupBottomNav()
        setupViewModel()
    }

    private fun setupBottomNav() {
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupViewModel() {

    }
}