package pl.toboche.feature.splash

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result

class TestLaunchPadsRepository : LaunchPadsRepository {
    val launchPadsFlow: MutableStateFlow<Result<List<LaunchPad>>> =
        MutableStateFlow(Result.NotStarted)

    var scheduledValue: Result<List<LaunchPad>> = Result.NotStarted

    override suspend fun getLaunchPads() {
        launchPadsFlow.value = scheduledValue
    }

    override val launchPads: StateFlow<Result<List<LaunchPad>>>
        get() = launchPadsFlow
}