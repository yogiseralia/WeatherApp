package io.github.yogiseralia.weatherapp.preferences

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.yogiseralia.weatherapp.AppSettings
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Suppress("UNCHECKED_CAST")
class PreferenceViewModelFactory(private val appDataStore: DataStore<AppSettings>?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            return PreferenceViewModel(appDataStore) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}