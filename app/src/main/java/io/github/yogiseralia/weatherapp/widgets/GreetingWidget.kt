package io.github.yogiseralia.weatherapp.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val TAG = "GreetingWidget"

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
@Preview
fun GreetingPreview() {
    Greeting(name = "Yogesh",
        Modifier
            .padding(16.dp)
            .clickable {
                Log.d(TAG, "GreetingPreview: clicked")
            }
    )
}