package io.github.yogiseralia.weatherapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.yogiseralia.weatherapp.screens.utils.Outcome
import io.github.yogiseralia.weatherapp.currentweather.FetchWeatherByLocationUseCase
import io.github.yogiseralia.weatherapp.currentweather.Latlng
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val fetchWeatherByLocationUseCase: FetchWeatherByLocationUseCase) :
    ViewModel() {

    fun getWeather(lat: Double, lng: Double) = liveData(Dispatchers.IO) {
        emit(Outcome.loading(data = null))

        try {
            emit(
                Outcome.success(
                    data = fetchWeatherByLocationUseCase.fetchWeatherByLocation(
                        Latlng(lat, lng)
                    )
                )
            )
        } catch (e: Exception) {
            emit(Outcome.error(data = null, message = e.localizedMessage ?: "Error Occurred!"))
            e.printStackTrace()
        }

    }
}