package io.github.yogiseralia.weatherapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.networking.APIService
import io.github.yogiseralia.weatherapp.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.screens.utils.Status.*
import io.github.yogiseralia.weatherapp.screens.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    lateinit var mainViewModel: MainViewModel

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                WeatherRepository(
                    APIService.getOpenWeatherMap(),
                    (application as WeatherApp).appDatabase.weatherDao()
                )
            )
        ).get(MainViewModel::class.java)
    }
}