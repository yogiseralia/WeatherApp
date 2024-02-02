package io.github.yogiseralia.weatherapp.preferences

import androidx.datastore.core.DataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.yogiseralia.weatherapp.AppSettings
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class PreferenceViewModel(private val appDataStore: DataStore<AppSettings>?) : ViewModel() {
    val unitOfTemp = MutableLiveData<AppSettings.TempUnit>()
    val unitOfWindSpeed = MutableLiveData<AppSettings.WindSpeedUnit>()
    val is24HrTime = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getSettings() {
        viewModelScope.launch {
            try {
                appDataStore?.data?.collect { value ->
                    is24HrTime.postValue(value.is24HrTime)
                    unitOfTemp.postValue(value.tempUnit)
                    unitOfWindSpeed.postValue(value.windSpeed)
                }
            } catch (e: Exception) {
                error.postValue(e.message)
            }
            // You should also handle IOExceptions here.
        }
    }
}