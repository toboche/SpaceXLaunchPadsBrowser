package pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details.LaunchPadDetailsViewModel

@Composable
fun LaunchPadDetailsScreen(
    navHostController: NavHostController,
    launchPadDetailsViewModel: LaunchPadDetailsViewModel = hiltViewModel()
) {
    val launchPadDetails: LaunchPadDetailsScreenState by launchPadDetailsViewModel.uiState.collectAsState(
        LaunchPadDetailsScreenState.Empty
    )
    Column {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navHostController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                (launchPadDetails as? LaunchPadDetailsScreenState.LaunchPadDetails)?.let {
                    Text(it.name)
                }
            }
        }
        (launchPadDetails as? LaunchPadDetailsScreenState.LaunchPadDetails)?.let {
            DetailsItem(it.fullName, "Full Name:")
            DetailsItem(it.status, "Status:")
            DetailsItem(value = it.location, title = "Location:")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DetailsItem(value: String, title: String) {
    ListItem(text = { Text(title) },
        secondaryText = {
            Text(
                text = value,
                style = MaterialTheme.typography.h5,
            )
        })

}

sealed interface LaunchPadDetailsScreenState {
    data class LaunchPadDetails(
        val name: String,
        val status: String,
        val location: String,
        val fullName: String
    ) : LaunchPadDetailsScreenState

    object Empty : LaunchPadDetailsScreenState
}
