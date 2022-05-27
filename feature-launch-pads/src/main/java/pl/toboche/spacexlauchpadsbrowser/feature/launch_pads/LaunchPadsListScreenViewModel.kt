package pl.toboche.spacexlauchpadsbrowser.feature.launch_pads

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import javax.inject.Inject

@HiltViewModel
class LaunchPadsListScreenViewModel @Inject constructor(
    launchPadsRepository: LaunchPadsRepository,
) : ViewModel() {

    val uiState: Flow<List<LaunchPadListItem>> = launchPadsRepository.launchPads.map { it ->
        if (it !is Result.Success) {
            return@map emptyList()
        }
        it.data.map {
            LaunchPadListItem(
                id = it.id,
                name = it.name,
                status = mapStatus(it)
            )
        }
    }

    //I'd propose to have these resources behind an interface or at least moved to an xml
    //in anything larger than this project
    //Also - this logic could be a standalone mapper class that'd be tested in separate
    private fun mapStatus(launchPad: LaunchPad) = when (launchPad.status) {
        LaunchPad.Status.UNKNOWN -> "Unknown"
        LaunchPad.Status.RETIRED -> "Retired"
        LaunchPad.Status.ACTIVE -> "Active"
        LaunchPad.Status.UNDER_CONSTRUCTION -> "Under construction"
    }

}
