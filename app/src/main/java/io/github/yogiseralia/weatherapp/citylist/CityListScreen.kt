package io.github.yogiseralia.weatherapp.citylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.yogiseralia.weatherapp.R

@Composable
fun CityListScreen(onBack: () -> Boolean, onCitySelected: (String) -> Unit, onAddCity: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.city_list_title))
        LazyColumn {
            items(10) {
                Text(
                    text = "City $it",
                    modifier = Modifier.clickable { onCitySelected("City $it") }
                )
            }
        }
    }
}
