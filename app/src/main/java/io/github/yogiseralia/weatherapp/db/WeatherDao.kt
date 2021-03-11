package io.github.yogiseralia.weatherapp.db

import androidx.room.*


@Dao
interface WeatherDao {
    @Query("SELECT tempInK from Weather limit 1")
    fun getTemp(): Int

    @Query("SELECT * from Weather limit 1")
    fun getWeatherEntity(): WeatherEntity?

    @Insert
    fun saveTemp(weatherEntity: WeatherEntity): Long

    @Update
    fun updateTemp(weatherEntity: WeatherEntity)

    @Delete
    fun delete(weatherEntity: WeatherEntity)
}