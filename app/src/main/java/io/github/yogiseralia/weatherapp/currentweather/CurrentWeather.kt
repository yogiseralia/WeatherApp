package io.github.yogiseralia.weatherapp.currentweather

data class CurrentWeather(
    val temperature: Double,
    val status: String,
    val humidity: Int,
    val iconId: Int,
    val iconName: String,
    val weeklyWeather : List<WeekdayWeather>
)