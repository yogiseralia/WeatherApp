package io.github.yogiseralia.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.utils.SpeedUnit

@Composable
fun HumidityWindFeelLikeWidget(
    humidityPercent: Int,
    windSpeed: Int,
    windSpeedUnit: SpeedUnit,
    feelLikeTemp: Int
) {
    val windSpeedString = when (windSpeedUnit) {
        SpeedUnit.KMS_PER_HOUR -> "$windSpeed km/h"
        SpeedUnit.MILES_PER_HOUR -> "$windSpeed mph"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.wi_humidity),
                contentDescription = "Humidity Percent"
            )
            Text(text = "HUMIDITY", fontSize = 14.sp)
            Text(text = "$humidityPercent%", fontSize = 14.sp)
        }
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.wi_strong_wind),
                contentDescription = "Wind Speed"
            )
            Text(text = "WIND", fontSize = 14.sp)
            Text(text = "$windSpeedString", fontSize = 14.sp)
        }
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.wi_thermometer),
                contentDescription = "Feels Like Temperature"
            )
            Text(text = "FEELS LIKE", fontSize = 14.sp)
            Text(text = "$feelLikeTemp°", fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun HumidityWindFeelLikeWidgetDemo() {
    HumidityWindFeelLikeWidget(
        humidityPercent = 50,
        windSpeed = 10,
        windSpeedUnit = SpeedUnit.KMS_PER_HOUR,
        feelLikeTemp = 25
    )
}