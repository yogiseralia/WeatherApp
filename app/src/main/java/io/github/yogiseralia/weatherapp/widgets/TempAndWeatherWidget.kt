package io.github.yogiseralia.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.utils.TempUnit

enum class WeatherType {
    SUNNY, CLOUDS, MIST, HAZE, RAINY, SNOWY, STORM
}

@Composable
private fun iconToWeatherName(
    weatherType: WeatherType,
    isDay: Boolean
) = when (weatherType) {
    WeatherType.SUNNY -> (if (isDay) R.drawable.wi_day_sunny else R.drawable.wi_night_clear) to "Clear"
    WeatherType.CLOUDS -> (if (isDay) R.drawable.wi_day_cloudy else R.drawable.wi_night_alt_cloudy) to "Clouds"
    WeatherType.MIST -> (if (isDay) R.drawable.wi_day_fog else R.drawable.wi_night_fog) to "Mist"
    WeatherType.HAZE -> (if (isDay) R.drawable.wi_day_haze else R.drawable.wi_day_haze) to "Haze"
    WeatherType.RAINY -> (if (isDay) R.drawable.wi_day_rain else R.drawable.wi_night_alt_rain) to "Rain"
    WeatherType.SNOWY -> (if (isDay) R.drawable.wi_day_snow else R.drawable.wi_night_alt_snow) to "Snow"
    WeatherType.STORM -> (if (isDay) R.drawable.wi_day_thunderstorm else R.drawable.wi_night_alt_thunderstorm) to "Thunderstorm"
}


@Composable
fun TempAndWeatherWidget(
    weatherType: WeatherType,
    temp: String,
    tempUnit: TempUnit,
    isDay: Boolean = true
) {
    val (icon, weatherName) = iconToWeatherName(weatherType, isDay)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = icon), contentDescription = weatherName)
        Text(text = AnnotatedString(weatherName), fontSize = 24.sp)
        Row {
            Text(text = temp, fontSize = 40.sp)
            Text(
                text = if (tempUnit == TempUnit.CELSIUS) "°C" else "°F",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Preview
@Composable
fun TempAndWeatherWidgetDemo() {
    TempAndWeatherWidget(
        weatherType = WeatherType.CLOUDS,
        temp = "24",
        tempUnit = TempUnit.FAHRENHEIT
    )
}