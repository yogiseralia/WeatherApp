package io.github.yogiseralia.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yogiseralia.weatherapp.R


@Composable
fun PickLocationWidget(cityName: String) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_location_pin),
            contentDescription = "Location Pin",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = cityName)
    }
}

@Preview
@Composable
fun PickLocationWidgetDemo() {
    PickLocationWidget("Paris")
}