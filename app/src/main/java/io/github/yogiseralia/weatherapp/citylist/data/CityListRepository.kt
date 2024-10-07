package io.github.yogiseralia.weatherapp.citylist.data

import io.github.yogiseralia.weatherapp.data.networking.APIResult
import kotlinx.coroutines.flow.Flow

interface CityListRepository {
    fun getCitySuggestionList(searchInput: String): Flow<APIResult<List<City>>>
}