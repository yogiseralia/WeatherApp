package io.github.yogiseralia.weatherapp.citylist.data

import io.github.yogiseralia.weatherapp.data.networking.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityListRepositoryImpl(private val citySuggestionAPiInterface: CitySuggestionAPiInterface) :
    CityListRepository {
    override fun getCitySuggestionList(searchInput: String): Flow<APIResult<List<City>>> = flow {
        emit(APIResult.Loading(isLoading = true))
        try {
            val response = citySuggestionAPiInterface.suggestCity(searchInput)
            emit(APIResult.Success(response))
        } catch (e: Exception) {
            emit(APIResult.Error(e.message.orEmpty(), e))
        } finally {
            emit(APIResult.Loading(isLoading = false))
        }
    }
}