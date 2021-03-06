package pl.toboche.spacexlauchpadsbrowser.core.data.repository

import kotlinx.coroutines.flow.StateFlow
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result

interface LaunchPadsRepository {
    suspend fun refreshLaunchPads()

    val launchPads: StateFlow<Result<List<LaunchPad>>>
}
