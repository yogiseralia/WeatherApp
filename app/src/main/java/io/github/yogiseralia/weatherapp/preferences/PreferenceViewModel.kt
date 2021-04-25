package io.github.yogiseralia.weatherapp.preferences

import androidx.datastore.core.DataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.yogiseralia.weatherapp.AppSettings
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class PreferenceViewModel(val appDataStore: DataStore<AppSettings>?) : ViewModel() {
    private val TAG = "PreferenceViewModel"
    val unitOfTemp = MutableLiveData<AppSettings.TempUnit>()
    val unitOfWindSpeed = MutableLiveData<AppSettings.WindSpeedUnit>()
    val is24HrTime = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getSettings() {
        viewModelScope.launch {
            try {
                appDataStore?.data?.collect(object : FlowCollector<AppSettings> {
                    override suspend fun emit(value: AppSettings) {
                        is24HrTime.postValue(value.is24HrTime)
                        unitOfTemp.postValue(value.tempUnit)
                        unitOfWindSpeed.postValue(value.windSpeed)
                    }
                })
            } catch (e: Exception) {
                error.postValue(e.message)
            }
            // You should also handle IOExceptions here.
        }
    }
}

@InternalCoroutinesApi
@Suppress("UNCHECKED_CAST")
class PreferenceViewModelFactory(val appDataStore: DataStore<AppSettings>?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            return PreferenceViewModel(appDataStore) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
