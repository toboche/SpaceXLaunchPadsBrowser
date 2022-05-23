package pl.toboche.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(
    val launchPadsRepository: LaunchPadsRepository
) : ViewModel() {
    val uiState: StateFlow<SplashScreenUiState> =
        launchPadsRepository.getLaunchPads()
            .transform {
                emit(
                    when (it) {
                        is Result.Loading -> SplashScreenUiState.Loading
                        is Result.Success -> closeSplashScreen()
                        is Result.Error -> SplashScreenUiState.Error("Error loading", "Retry")
                    }
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SplashScreenUiState.Loading
            )

    private fun closeSplashScreen(): SplashScreenUiState {
        //TODO
        return SplashScreenUiState.Finish
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