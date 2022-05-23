package pl.toboche.spacexlauchpadsbrowser.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import pl.toboche.feature.splash.SplashScreen
import pl.toboche.spacexlauchpadsbrowser.MainScreenViewModel
import pl.toboche.spacexlauchpadsbrowser.R
import pl.toboche.spacexlauchpadsbrowser.feature.launchpads.LaunchPadsListScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val uiState: MainScreenViewModel.MainScreenUiState by viewModel.uiState.collectAsState()
    when (uiState) {
        MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_CACHED -> LaunchPadsListScreen(uiState)
        MainScreenViewModel.MainScreenUiState.ERROR -> LoadingErrorScreen()
        MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_MISSING -> SplashScreen(R.drawable.ic_launcher_foreground)
    }
}