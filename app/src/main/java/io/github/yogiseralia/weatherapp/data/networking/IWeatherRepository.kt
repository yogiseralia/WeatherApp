package io.github.yogiseralia.weatherapp.data.networking

import io.github.yogiseralia.weatherapp.currentweather.Latlng
import io.github.yogiseralia.weatherapp.data.db.WeatherEntity

interface IWeatherRepository {
    suspend fun getWeather(latlng: Latlng): WeatherEntity?
}