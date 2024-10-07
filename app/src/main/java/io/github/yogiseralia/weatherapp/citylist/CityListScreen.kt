package io.github.yogiseralia.weatherapp.citylist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.yogiseralia.weatherapp.R

@Composable
fun CityListScreen(
    cityListViewModel: CityListViewModel = viewModel(factory = CityListViewModel.Factory),
    onBack: () -> Boolean,
    onCitySelected: (String) -> Unit,
    onAddCity: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.city_list_title),
                    modifier = Modifier.clickable { })
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    modifier = Modifier.clickable { },
                    contentDescription = null
                )
            }
            LazyColumn {
                items(12) {
                    CityListItem(
                        data = CityListItemData(
                            cityName = "Paris",
                            weather = "Wind",
                            temperature = "24 C",
                            icon = R.drawable.wi_fog,
                            humidity = "12%",
                            windSpeed = "4.63km/h"
                        ),
                        onCitySelected = {
                            onCitySelected(it.cityName)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = onAddCity) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        painter = painterResource(id = R.drawable.ic_add_new_city),
                        contentDescription = null
                    )
                    Text(text = "Add New")
                }
            }
        }
    }
}

@Composable
private fun CityListItem(data: CityListItemData, onCitySelected: (CityListItemData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCitySelected(data) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Column {
                    Text(text = data.cityName)
                    Text(text = data.weather)
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Column {
                    Text(text = "Humidity ${data.humidity}")
                    Text(text = "Wind ${data.windSpeed}")
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = data.icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = data.temperature)
            }
        }
    }
}

data class CityListItemData(
    val cityName: String,
    val weather: String,
    val temperature: String,
    val icon: Int,
    val humidity: String,
    val windSpeed: String
)

@Preview
@Composable
fun CityListScreenPreview() {
    CityListScreen(onBack = { true }, onCitySelected = {}, onAddCity = {})
}