package pl.toboche.spacexlauchpadsbrowser.feature.launchpads

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pl.toboche.spacexlauchpadsbrowser.MainScreenViewModel

@Composable
fun LaunchPadsListScreen(uiState: MainScreenViewModel.MainScreenUiState) {
    Column {
        Text(text = uiState.name)

    }
}

@Preview
@Composable
fun Preview() {
    LaunchPadsListScreen(uiState = MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_MISSING)
}