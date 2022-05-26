package pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.LaunchPadListItem
import pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.LaunchPadsScreenViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchPadsListScreen(
    viewModel: LaunchPadsScreenViewModel = hiltViewModel(),
) {
    val launchPads: List<LaunchPadListItem> by viewModel.uiState.collectAsState(emptyList())
    LazyColumn {
        items(launchPads) { launchPad ->
            ListItem(text = { Text(launchPad.name) })
            Divider()
        }
    }
}

@Preview
@Composable
fun Preview() {
    LaunchPadsListScreen()
}