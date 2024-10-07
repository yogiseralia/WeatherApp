package io.github.yogiseralia.weatherapp.main

import io.github.yogiseralia.weatherapp.base.BaseUiState

sealed class MainUIState : BaseUiState() {
    object Loading : MainUIState()
    data class CurrentWeatherFetched(val data: String) : MainUIState()
}