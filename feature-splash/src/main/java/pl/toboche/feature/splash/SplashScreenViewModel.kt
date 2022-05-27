package pl.toboche.feature.splash

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
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(
    launchPadsRepository: LaunchPadsRepository
) : ViewModel() {
    val uiState: StateFlow<SplashScreenUiState> =
        launchPadsRepository.launchPads
            .transform {
                emit(
                    when (it) {
                        is Result.Loading, Result.NotStarted -> SplashScreenUiState.Loading
                        is Result.Success -> SplashScreenUiState.Finish
                        is Result.Error -> SplashScreenUiState.Error("Error loading", "Retry")
                    }
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SplashScreenUiState.Loading
            ).also {
                viewModelScope.launch {
                    launchPadsRepository.refreshLaunchPads()
                }
            }

    sealed interface SplashScreenUiState {
        object Loading : SplashScreenUiState
        object Finish : SplashScreenUiState
        data class Error(
            val errorText: String,
            val buttonText: String
        ) : SplashScreenUiState
    }
}