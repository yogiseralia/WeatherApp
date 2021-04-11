package io.github.yogiseralia.weatherapp.data.networking

import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherAPIService {
    @GET("weather")
    suspend fun weatherByCoordinates(@Query("lat") lat:Double, @Query("lon") lng:Double) : CurrentWeatherResponse

    @GET("onecall")
    suspend fun weatherByCoordinatesOneCall(
        @Query("lat") lat:Double,
        @Query("lon") lng:Double,
        @Query("exclude") exclude:String = "alerts"
    ) : OneCallAPIResponse
}

