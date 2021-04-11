package io.github.yogiseralia.weatherapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.yogiseralia.weatherapp.currentweather.FetchWeatherByLocationUseCase
import io.github.yogiseralia.weatherapp.currentweather.screens.CurrentWeatherViewModel
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository

class CurrentWeatherScreenViewModelFactory(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)) {
            return CurrentWeatherViewModel(FetchWeatherByLocationUseCase(weatherRepository)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}