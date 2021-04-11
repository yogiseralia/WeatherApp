package io.github.yogiseralia.weatherapp.data.networking

import io.github.yogiseralia.weatherapp.currentweather.Latlng
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherEntityRepositoryTest : TestCase() {

    lateinit var openWeatherAPIService: OpenWeatherAPIService
    val latlng: Latlng
        get() {
            return Latlng(27.5530, 76.6346)
        }

    @Before
    fun setup() {

    }

    @Test
    fun testGetOneCallWeather() = runBlocking {
        openWeatherAPIService = APIService.getOpenWeatherAPI()
        val weatherByCoordinatesOneCall: OneCallAPIResponse =
            openWeatherAPIService.weatherByCoordinatesOneCall(latlng.lat, latlng.lng)
        println(weatherByCoordinatesOneCall)
    }
}