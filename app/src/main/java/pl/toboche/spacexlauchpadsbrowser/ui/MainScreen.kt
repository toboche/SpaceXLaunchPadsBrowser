package pl.toboche.spacexlauchpadsbrowser.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.toboche.feature.splash.SplashScreen
import pl.toboche.spacexlauchpadsbrowser.MainScreenViewModel
import pl.toboche.spacexlauchpadsbrowser.R
import pl.toboche.spacexlauchpadsbrowser.core.navigation.Navigation

@Composable
fun MainScreen(
    padding: PaddingValues,
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val uiState: MainScreenViewModel.MainScreenUiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.padding(padding))
    when (uiState) {
        is MainScreenViewModel.MainScreenUiState.Initial -> CircularProgressIndicator()
        is MainScreenViewModel.MainScreenUiState.Cached -> Navigation()
        is MainScreenViewModel.MainScreenUiState.Missing -> SplashScreen(R.drawable.ic_launcher_foreground)
        is MainScreenViewModel.MainScreenUiState.Error -> showErrorDialog(
            uiState as MainScreenViewModel.MainScreenUiState.Error,
            viewModel
        )
    }
}

@Composable
private fun showErrorDialog(
    uiState: MainScreenViewModel.MainScreenUiState.Error,
    viewModel: MainScreenViewModel
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = uiState.title)
            },
            text = {
                Text(uiState.description)
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            openDialog.value = false
                            viewModel.refreshData()
                        }
                    ) {
                        Text(uiState.actionText)
                    }
                }
            }
        )
    }
}
