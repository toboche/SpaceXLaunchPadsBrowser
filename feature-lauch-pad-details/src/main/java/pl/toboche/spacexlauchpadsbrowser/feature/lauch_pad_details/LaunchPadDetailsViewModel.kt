package pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details.ui.LaunchPadDetailsScreenState
import javax.inject.Inject

@HiltViewModel
class LaunchPadDetailsViewModel @Inject constructor(
    launchPadsRepository: LaunchPadsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _launchPadId: MutableStateFlow<Long> = MutableStateFlow<Long>(-1)
        .also { it.tryEmit(savedStateHandle.get<Long>("launchPadId")!!) }

    val uiState: Flow<LaunchPadDetailsScreenState> = _launchPadId.map { id ->
        if (id == -1L) {
            LaunchPadDetailsScreenState.Empty
        } else {
            val launchPad =
                (launchPadsRepository.launchPads.value as Result.Success<List<LaunchPad>>).data.first { it.id == id }
            LaunchPadDetailsScreenState.LaunchPadDetails(
                name = launchPad.name,
                status = launchPad.status.toString(),
                location = "${launchPad.location.name}, ${launchPad.location.region}",
                fullName = launchPad.fullName,
            )
        }
    }
}
