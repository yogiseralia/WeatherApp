package io.github.yogiseralia.weatherapp.currentweather

import io.github.yogiseralia.weatherapp.data.db.DBDailyItem
import io.github.yogiseralia.weatherapp.data.db.WeatherEntity
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.utils.Outcome
import io.github.yogiseralia.weatherapp.utils.TimeUtils

class FetchWeatherByLocationUseCase(private val mWeatherRepository: WeatherRepository) {
    private val TAG = "FetchWeatherByLocationUseCase"

    suspend fun fetchWeatherByLocation(latlng: Latlng): Outcome<CurrentWeather?> {
        return try {
            val weather: WeatherEntity? = mWeatherRepository.getWeather(latlng)
            weather?.let {
                Outcome.success(
                    CurrentWeather(
                        it.DBCurrent.temp,
                        it.DBCurrent.DBWeather[0].description,
                        it.DBCurrent.humidity,
                        it.DBCurrent.DBWeather[0].id,
                        it.DBCurrent.DBWeather[0].icon,
                        convertWeekday(it.DBDaily)
                    )
                )
            } ?: Outcome.error(null, "data not found in db locally")
        } catch (e: Exception) {
            return Outcome.error(null, e.localizedMessage.orEmpty())
        }
    }

    private fun convertWeekday(dbDailyItem: List<DBDailyItem>): List<WeekdayWeather> {
        val weekdayList = arrayListOf<WeekdayWeather>()
        dbDailyItem.map {
            val dbWeatherItem = it.DBWeather[0]
            weekdayList.add(
                WeekdayWeather(
                    TimeUtils.getFormattedTime(it.dt, dbDailyItem.indexOf(it)),
                    TimeUtils.getFormattedDate(dbDailyItem.indexOf(it)),
                    dbWeatherItem.id,
                    it.DBTemp.max,
                    it.DBTemp.min,
                    dbWeatherItem.main,
                    dbWeatherItem.description,
                    it.pop,
                    it.windDeg,
                    it.windSpeed,
                    it.uvi
                )
            )
        }
        return weekdayList
    }
}