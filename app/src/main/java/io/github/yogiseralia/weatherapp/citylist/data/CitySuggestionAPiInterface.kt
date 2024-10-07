package io.github.yogiseralia.weatherapp.citylist.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CitySuggestionAPiInterface {

    @GET("geo/1.0/direct")
    fun suggestCity(
        @Query("q") query: String,
        @Query("limit") limit: Int = 5
    ): List<City>

}