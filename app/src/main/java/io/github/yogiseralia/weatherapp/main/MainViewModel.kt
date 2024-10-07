package io.github.yogiseralia.weatherapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import io.github.yogiseralia.weatherapp.base.BaseViewModel

class MainViewModel : BaseViewModel<MainUIState, MainUIEvent>() {

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return MainViewModel() as T
            }
        }
    }

    override fun onUIEvent(uiEvent: MainUIEvent) {
        when (uiEvent) {
            MainUIEvent.FetchCurrentWeather -> sendUIState(MainUIState.CurrentWeatherFetched("Data"))
        }
    }
}