package io.github.yogiseralia.weatherapp.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class ScreenSharedViewModel : ViewModel() {
    var cityName: String = "Initial Data"

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ScreenSharedViewModel()
            }
        }
    }
}
