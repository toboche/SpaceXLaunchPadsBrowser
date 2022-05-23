package pl.toboche.spacexlauchpadsbrowser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingErrorScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Error loading")
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LoadingErrorScreen()
}