package io.github.yogiseralia.weatherapp.screens.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.yogiseralia.weatherapp.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.screens.MainViewModel
import io.github.yogiseralia.weatherapp.currentweather.FetchWeatherByLocationUseCase

class ViewModelFactory(val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(FetchWeatherByLocationUseCase(weatherRepository)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}