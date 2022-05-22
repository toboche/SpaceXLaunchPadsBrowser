package pl.toboche.spacexlauchpadsbrowser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.core.result.Result.Loading
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
    val launchPadsRepository: LaunchPadsRepository
) : ViewModel() {

    val uiState: StateFlow<MainScreenUiState> =
        launchPadsRepository.getLaunchPads().transform<Result<List<LaunchPad>>, MainScreenUiState> {
            emit(
                when (it) {
                    is Loading -> MainScreenUiState.LAUNCH_PADS_MISSING
                    is Result.Success -> MainScreenUiState.LAUNCH_PADS_CACHED
                    is Result.Error -> MainScreenUiState.ERROR
                }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainScreenUiState.ERROR
        )


    enum class MainScreenUiState {
        LAUNCH_PADS_CACHED,
        LAUNCH_PADS_MISSING,
        ERROR,
    }
}