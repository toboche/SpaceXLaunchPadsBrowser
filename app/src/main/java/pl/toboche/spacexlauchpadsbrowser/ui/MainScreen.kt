package pl.toboche.spacexlauchpadsbrowser.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import pl.toboche.feature.splash.SplashScreen
import pl.toboche.feature.splash.SplashScreenViewModel
import pl.toboche.spacexlauchpadsbrowser.MainScreenViewModel
import pl.toboche.spacexlauchpadsbrowser.R
import pl.toboche.spacexlauchpadsbrowser.feature.launchpads.LaunchPadsListScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    viewModel2: SplashScreenViewModel = hiltViewModel(),
) {
    val uiState: MainScreenViewModel.MainScreenUiState by viewModel.uiState.collectAsState()
    val uiState2: SplashScreenViewModel.SplashScreenUiState by viewModel2.uiState.collectAsState()


    when (uiState) {
        MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_CACHED ->
            LaunchPadsListScreen(uiState)
        MainScreenViewModel.MainScreenUiState.LAUNCH_PADS_MISSING,
        MainScreenViewModel.MainScreenUiState.ERROR ->
            SplashScreen(R.drawable.ic_launcher_foreground)
    }
}