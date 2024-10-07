package io.github.yogiseralia.weatherapp.citylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.yogiseralia.weatherapp.citylist.data.CityListRepository
import io.github.yogiseralia.weatherapp.citylist.data.CityListRepositoryImpl
import io.github.yogiseralia.weatherapp.data.networking.APIResult
import io.github.yogiseralia.weatherapp.data.networking.APIService
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CityListViewModel(private val cityListRepository: CityListRepository) : ViewModel() {

    fun getCitySuggestionList(searchInput: String) {
        cityListRepository.getCitySuggestionList(searchInput).onEach { response ->
            when (response) {
                is APIResult.Loading -> {}

                is APIResult.Success -> {

                }

                is APIResult.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CityListViewModel(
                    cityListRepository = CityListRepositoryImpl(APIService.getCitySuggestionsAPIInterface())
                )
            }
        }
    }
}