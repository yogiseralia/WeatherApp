package io.github.yogiseralia.weatherapp.currentweather

data class WeekdayWeather(
    val formattedTime: String,
    val formattedDate: String,
    val iconId: Int,
    val maxTemp: Double,
    val minTemp: Double,
    val mainCondition: String,
    val descriptiveCondition: String,
    val precipitation_percent: Double,
    val windDeg: Int,
    val windSpeed: Double,
    val uvi: Double
)
