package io.github.yogiseralia.weatherapp.currentweather

import android.annotation.SuppressLint
import android.util.Log
import io.github.yogiseralia.weatherapp.db.WeatherEntity
import io.github.yogiseralia.weatherapp.networking.WeatherRepository
import java.text.SimpleDateFormat
import java.util.*

class FetchWeatherByLocationUseCase(private val mWeatherRepository: WeatherRepository) {
    private val TAG = "FetchWeatherByLocationU"

    suspend fun fetchWeatherByLocation(latlng: Latlng): CurrentWeather {
        return mapDBToUI(mWeatherRepository.getWeather(latlng))
    }

    @SuppressLint("SimpleDateFormat")
    private fun mapDBToUI(dbData: WeatherEntity): CurrentWeather {
        val date: Date = Date(dbData.timeInMills.toLong() + 19800000)

        val simpleDateFormat = SimpleDateFormat("hh:mm:ss a")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        Log.d(TAG,simpleDateFormat.format(date));
        return CurrentWeather((dbData.tempInK - 273).toDouble())
    }
}