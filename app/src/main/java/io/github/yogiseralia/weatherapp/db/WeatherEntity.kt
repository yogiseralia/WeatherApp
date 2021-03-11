package io.github.yogiseralia.weatherapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @ColumnInfo(name = "tempInK") val tempInK: Int,
    @ColumnInfo(name = "tempFeelsLikeInK") val tempFeelsLikeInK: Int,
    @ColumnInfo(name = "tempMaxInK") val tempMaxInK: Int,
    @ColumnInfo(name = "tempMinInK") val tempMinInK: Int,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "windDeg") val windDeg: Int,
    @ColumnInfo(name = "windSpeed") val windSpeed: Int,
    @ColumnInfo(name = "sunrise") val sunrise: Int,
    @ColumnInfo(name = "sunset") val sunset: Int,
    @ColumnInfo(name = "timeInMills") val timeInMills: Int,
    @ColumnInfo(name = "timezone") val timezone: Int,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double
)