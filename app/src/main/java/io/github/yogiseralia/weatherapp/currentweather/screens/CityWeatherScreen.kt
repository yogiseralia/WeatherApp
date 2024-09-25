package io.github.yogiseralia.weatherapp.currentweather.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.yogiseralia.weatherapp.utils.SpeedUnit
import io.github.yogiseralia.weatherapp.utils.TempUnit
import io.github.yogiseralia.weatherapp.widgets.DateTimeWidget
import io.github.yogiseralia.weatherapp.widgets.HamburgerWidget
import io.github.yogiseralia.weatherapp.widgets.HumidityWindFeelLikeWidget
import io.github.yogiseralia.weatherapp.widgets.PickLocationWidget
import io.github.yogiseralia.weatherapp.widgets.TempAndWeatherWidget
import io.github.yogiseralia.weatherapp.widgets.WeatherType

@Composable
fun CityWeatherScreen(cityName: String, onHamburgerCLick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            PickLocationWidget(cityName = cityName)
            HamburgerWidget(onClick = onHamburgerCLick)
        }
        DateTimeWidget("07 June", "Updated 6/7/2023 4:55 PM")
        TempAndWeatherWidget(
            weatherType = WeatherType.CLOUDS,
            temp = "25.0",
            tempUnit = TempUnit.CELSIUS
        )
        HumidityWindFeelLikeWidget(
            humidityPercent = 52,
            windSpeed = 15,
            windSpeedUnit = SpeedUnit.KMS_PER_HOUR,
            feelLikeTemp = 24
        )
    }
}

@Preview
@Composable
fun CityWeatherScreenPreview() {
    CityWeatherScreen(cityName = "Paris", onHamburgerCLick = {})
}