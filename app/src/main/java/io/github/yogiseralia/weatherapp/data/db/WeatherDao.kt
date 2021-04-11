package io.github.yogiseralia.weatherapp.data.db

import androidx.room.*


@Dao
interface WeatherDao {
    @Query("SELECT current from Weather limit 1")
    fun getTemp(): Int

    @Query("SELECT * from Weather limit 1")
    fun getWeatherEntity(): WeatherEntity?

    @Insert
    fun saveWeather(weatherEntity: WeatherEntity): Long

    @Update
    fun updateWeather(weatherEntity: WeatherEntity)

    @Delete
    fun delete(weatherEntity: WeatherEntity)
}