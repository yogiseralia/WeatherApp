package io.github.yogiseralia.weatherapp.navigation

enum class Screen(val route: String) {
    CITY_WEATHER("weather/{cityName}"),
    ADD_CITY("addCity"),
    CITY_LIST("cityList");

    companion object {
        fun CityWeather(cityName: String): String = CITY_WEATHER.route.replace("{cityName}", cityName)
    }
}