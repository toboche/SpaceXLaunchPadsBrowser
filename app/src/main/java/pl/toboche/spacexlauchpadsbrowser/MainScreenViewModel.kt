package pl.toboche.spacexlauchpadsbrowser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.core.result.Result.Loading
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
    private val launchPadsRepository: LaunchPadsRepository
) : ViewModel() {
    val uiState: StateFlow<MainScreenUiState> =
        launchPadsRepository.launchPads.transform {
            emit(
                when (it) {
                    is Loading, Result.NotStarted -> MainScreenUiState.Missing
                    is Result.Success -> MainScreenUiState.Cached
                    is Result.Error -> MainScreenUiState.Error(
                        "Error downloading",
                        "Tap retry to try one more time",
                        "Retry"
                    )
                }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainScreenUiState.Initial
        ).also {
            refreshData()
        }

    fun refreshData() {
        viewModelScope.launch {
            launchPadsRepository.refreshLaunchPads()
        }
    }

    sealed interface MainScreenUiState {
        object Cached : MainScreenUiState
        object Missing : MainScreenUiState
        object Initial : MainScreenUiState
        data class Error(
            val title: String,
            val description: String,
            val actionText: String,
        ) : MainScreenUiState
    }
}