package pl.toboche.spacexlauchpadsbrowser.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import pl.toboche.feature.splash.SplashScreen
import pl.toboche.spacexlauchpadsbrowser.MainScreenViewModel
import pl.toboche.spacexlauchpadsbrowser.R

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val uiState: MainScreenViewModel.MainScreenUiState by viewModel.uiState.collectAsState()
    if (uiState == MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_CACHED) {
        Text(text = uiState.name)
    } else {
        SplashScreen(R.drawable.ic_launcher_foreground)
    }
}