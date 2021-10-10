package io.github.yogiseralia.weatherapp.preferences

object SettingsPrefs {
    fun getInSelectedTempUnit(temp: Double): String {
        return kelvinToCelsius(temp).toInt().toString() + "°"
    }

    fun kelvinToCelsius(temp: Double): Double {
        return temp - 273
    }

    fun kelvinToFahrenheit(temp: Double): Double {
        return temp - 273
    }
}
