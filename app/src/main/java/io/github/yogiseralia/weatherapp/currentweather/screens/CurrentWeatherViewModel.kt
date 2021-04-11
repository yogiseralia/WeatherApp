package io.github.yogiseralia.weatherapp.currentweather.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.yogiseralia.weatherapp.currentweather.FetchWeatherByLocationUseCase
import io.github.yogiseralia.weatherapp.currentweather.Latlng
import io.github.yogiseralia.weatherapp.utils.Outcome
import kotlinx.coroutines.Dispatchers

class CurrentWeatherViewModel(private val fetchWeatherByLocationUseCase: FetchWeatherByLocationUseCase) :
    ViewModel() {

    fun getWeather(lat: Double, lng: Double) = liveData(Dispatchers.IO) {
        emit(Outcome.loading(data = null))
        emit(
            fetchWeatherByLocationUseCase.fetchWeatherByLocation(
                Latlng(lat, lng)
            )
        )
    }

}