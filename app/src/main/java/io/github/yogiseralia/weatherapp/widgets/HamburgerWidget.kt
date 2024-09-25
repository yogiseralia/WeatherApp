package io.github.yogiseralia.weatherapp.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.yogiseralia.weatherapp.R

private const val TAG = "HamburgerWidget"

@Composable
fun HamburgerWidget(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_hamburger),
        contentDescription = "Menu Icon",
        modifier = Modifier.clickable {
            onClick()
        })
}

@Preview()
@Composable
fun HamburgerWidgetDemo() {
    HamburgerWidget {
        Log.d(TAG, "HamburgerWidgetDemo: clicked")
    }
}