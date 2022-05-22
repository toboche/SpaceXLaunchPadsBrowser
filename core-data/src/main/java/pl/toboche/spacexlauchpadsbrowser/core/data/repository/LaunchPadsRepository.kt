package pl.toboche.spacexlauchpadsbrowser.core.data.repository

import kotlinx.coroutines.flow.Flow
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result

interface LaunchPadsRepository {
    fun getLaunchPads(): Flow<Result<List<LaunchPad>>>
}
