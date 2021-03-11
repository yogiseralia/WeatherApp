package io.github.yogiseralia.weatherapp.networking

import android.util.Log
import io.github.yogiseralia.weatherapp.db.WeatherDao
import io.github.yogiseralia.weatherapp.db.WeatherEntity
import io.github.yogiseralia.weatherapp.currentweather.Latlng

class WeatherRepository(
    private val mAPIService: OpenWeatherMapService,
    private val mDBService: WeatherDao
) {
    private val TAG = "WeatherRepository"

    suspend fun getWeather(latlng: Latlng): WeatherEntity {
        var weatherEntity: WeatherEntity? = mDBService.getWeatherEntity()
        if (weatherEntity == null) {
            weatherEntity =
                mapWeatherToDB(mAPIService.weatherByCoordinates(latlng.lat, latlng.lng))
            val saveTemp = mDBService.saveTemp(weatherEntity)
            Log.d(TAG, "saveTemp with id = $saveTemp")
        } else {
            weatherEntity = mDBService.getWeatherEntity()
//            val is1HourDifference: Boolean = getTimeDiff(Date().time, weatherEntity?.timeInMills)
//            if (is1HourDifference) {
//
//            }
        }
        return weatherEntity!!
    }

//    private fun getTimeDiff(date: Long, timeInMills: Int?): Boolean {
//
//    }

    private fun mapWeatherToDB(input: CurrentWeatherResponse): WeatherEntity {
        return WeatherEntity(
            0,
            input.main.temp.toInt(),
            input.main.feels_like.toInt(),
            input.main.temp_max.toInt(),
            input.main.temp_min.toInt(),
            input.main.pressure,
            input.main.humidity,
            input.wind.deg.toInt(),
            input.wind.speed.toInt(),
            input.sys.sunrise,
            input.sys.sunset,
            input.dt,
            input.timezone,
            input.coord.lat,
            input.coord.lon
        )
    }
}