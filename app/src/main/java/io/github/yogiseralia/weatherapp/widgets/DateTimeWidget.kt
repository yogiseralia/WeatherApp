package io.github.yogiseralia.weatherapp.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DateTimeWidget(date: String, dateWithTime: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = date,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = dateWithTime,
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodySmall.copy(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x330000FF)
@Composable
fun DateTimeWidgetDemo() {
    DateTimeWidget(date = "June 07", dateWithTime = "Updated 6/7/2023 4:55 PM")
}