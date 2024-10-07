package io.github.yogiseralia.weatherapp.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.rememberNavController
import io.github.yogiseralia.weatherapp.navigation.Screen
import io.github.yogiseralia.weatherapp.navigation.WeatherNavGraph
import io.github.yogiseralia.weatherapp.theme.WeatherTheme
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewModel
        ViewModelProvider(this)[MainViewModel::class.java].apply {
            onUIEvent(MainUIEvent.FetchCurrentWeather)
            uiStateFlow.flowWithLifecycle(
                lifecycle,
                minActiveState = Lifecycle.State.CREATED
            ).asLiveData(Dispatchers.Main).observe(this@MainActivity) {
                when (it) {
                    is MainUIState.Loading -> {

                    }

                    is MainUIState.CurrentWeatherFetched -> {

                    }
                }
            }
        }

        setContent {
            WeatherTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherNavGraph(
                        navController = navController,
                        onBackPressedDispatcher = onBackPressedDispatcher,
                        startDestination = Screen.CityWeather("Paris"),
                        onFinish = { finish() }
                    )
                }
            }
        }
    }

    @Composable
    private fun ShowLoading() {
    }
}