package io.github.yogiseralia.weatherapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<US : BaseUiState, UE : BaseUiEvent> : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UE>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _stateChannel = Channel<US>()
    val uiStateFlow = _stateChannel.receiveAsFlow()

    abstract fun onUIEvent(uiEvent: UE)

    protected fun sendUIState(uiState: US) {
        viewModelScope.launch {
            _stateChannel.send(uiState)
        }
    }
}