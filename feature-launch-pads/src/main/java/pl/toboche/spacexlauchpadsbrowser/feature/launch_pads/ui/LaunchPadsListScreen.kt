package pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import pl.toboche.spacexlauchpadsbrowser.core.navigation.LaunchPadScreens
import pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.LaunchPadListItem
import pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.LaunchPadsListScreenViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchPadsListScreen(
    navHostController: NavHostController,
    viewModel: LaunchPadsListScreenViewModel = hiltViewModel(),
) {
    val launchPads: List<LaunchPadListItem> by viewModel.uiState.collectAsState(emptyList())

    LazyColumn {
        items(launchPads) { launchPad ->
            ListItem(text = { Text(launchPad.name) }, modifier = Modifier.clickable {
                navHostController.navigate(LaunchPadScreens.DetailsScreen.route + "/${launchPad.id}")
            })
            Divider()
        }
    }
}