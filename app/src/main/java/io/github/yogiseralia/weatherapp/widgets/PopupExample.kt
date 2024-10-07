package io.github.yogiseralia.weatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun PopupExample() {
    // State to manage the visibility of the popup
    var showPopup by remember { mutableStateOf(false) }

    // Main content with a button to show the popup
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showPopup = true }) {
            Text("Show Popup")
        }

        // Show popup if the state is true
        if (showPopup) {
            Popup(onDismissRequest = { showPopup = false }) {
                // Popup content
                PopupContent(onDismiss = { showPopup = false })
            }
        }
    }
}

@Composable
fun PopupContent(onDismiss: () -> Unit) {
    // Sample list of items
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    // Container for popup content
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.onSurface)
    ) {
        Column {
            items.forEach { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            // Handle item click
                            onDismiss() // Close popup on item click
                        }
                )
            }
            Text(
                text = "Close",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDismiss() } // Close on clicking "Close"
            )
        }
    }
}

@Preview
@Composable
fun PopupExamplePreview() {
    PopupExample()
}