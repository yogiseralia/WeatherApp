package io.github.yogiseralia.weatherapp.networking

import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapService {
    @GET("weather")
    suspend fun weatherByCoordinates(@Query("lat") lat:Double, @Query("lon") lng:Double) : CurrentWeatherResponse
}

