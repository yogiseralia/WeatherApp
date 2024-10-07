package io.github.yogiseralia.weatherapp.main

import io.github.yogiseralia.weatherapp.base.BaseUiEvent

sealed class MainUIEvent : BaseUiEvent() {
    object FetchCurrentWeather : MainUIEvent()
}