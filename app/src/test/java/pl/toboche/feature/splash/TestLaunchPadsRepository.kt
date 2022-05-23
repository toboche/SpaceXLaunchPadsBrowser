package pl.toboche.feature.splash

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result

class TestLaunchPadsRepository : LaunchPadsRepository {
    val launchPadsFlow: MutableSharedFlow<Result<List<LaunchPad>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getLaunchPads(): Flow<Result<List<LaunchPad>>> = launchPadsFlow
}