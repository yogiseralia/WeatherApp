package io.github.yogiseralia.weatherapp.data.networking

import io.github.yogiseralia.weatherapp.currentweather.Latlng
import io.github.yogiseralia.weatherapp.data.db.WeatherDao
import io.github.yogiseralia.weatherapp.data.db.WeatherEntity
import java.util.*

class WeatherRepository(
    private val mAPIService: OpenWeatherAPIService,
    private val mDBService: WeatherDao,
    private val weatherAPIToDBMapper: WeatherAPIToDBMapper
) : IWeatherRepository {
    private val TAG = "WeatherRepository"

    override suspend fun getWeather(latlng: Latlng): WeatherEntity? {
        var weatherEntity: WeatherEntity? = mDBService.getWeatherEntity()
        if (isDataCached(weatherEntity)) {
            weatherEntity = weatherAPIToDBMapper.mapWeatherToDB(callAPI(latlng))
            mDBService.saveWeather(weatherEntity)
        } else if (isDataOldNow(weatherEntity)) {
            weatherEntity = weatherAPIToDBMapper.mapWeatherToDB(callAPI(latlng))
            mDBService.updateWeather(weatherEntity)
        }
        return weatherEntity
    }

    private fun isDataCached(weatherEntity: WeatherEntity?) = weatherEntity == null

    private suspend fun callAPI(latlng: Latlng) = mAPIService.weatherByCoordinatesOneCall(
        latlng.lat,
        latlng.lng
    )

    private fun isDataOldNow(weatherEntity: WeatherEntity?) =
        getDifferenceInMins(weatherEntity?.DBCurrent?.dt ?: 0) > 10

    private fun getDifferenceInMins(timeStamp: Int): Int {
        val currCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        cal.timeInMillis = timeStamp.toLong().times(1000)
        return currCal.get(Calendar.MINUTE) - cal.get(Calendar.MINUTE)
    }
}