package io.github.yogiseralia.weatherapp.currentweather

import io.github.yogiseralia.weatherapp.data.db.WeatherEntity
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.utils.Outcome

class FetchWeatherByLocationUseCase(private val mWeatherRepository: WeatherRepository) {
    private val TAG = "FetchWeatherByLocationUseCase"

    suspend fun fetchWeatherByLocation(latlng: Latlng): Outcome<CurrentWeather?> {
        return try {
            val weather: WeatherEntity? = mWeatherRepository.getWeather(latlng)
            weather?.let {
                Outcome.success(
                    CurrentWeather(
                        it.DBCurrent.temp,
                        it.DBCurrent.DBWeather[0].main,
                        it.DBCurrent.humidity,
                        it.DBCurrent.DBWeather[0].id,
                        it.DBCurrent.DBWeather[0].icon
                    )
                )
            } ?: Outcome.error(null, "data not found in db locally")
        } catch (e: Exception) {
            return Outcome.error(null, e.localizedMessage.orEmpty())
        }
    }
}