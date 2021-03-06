package pl.toboche.spacexlauchpadsbrowser.core.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import pl.toboche.spacexlauchpadsbrowser.core.data.mapping.LaunchPadsMapper
import pl.toboche.spacexlauchpadsbrowser.core.di.AppDispatchers
import pl.toboche.spacexlauchpadsbrowser.core.di.Dispatcher
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.network.LaunchPadsNetwork
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class MemoryCachedLaunchPadsRepository @Inject constructor(
    val launchPadsNetwork: LaunchPadsNetwork,
    val mapper: LaunchPadsMapper,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : LaunchPadsRepository {

    private val _launchPads = MutableStateFlow<Result<List<LaunchPad>>>(Result.NotStarted)

    override val launchPads: StateFlow<Result<List<LaunchPad>>> get() = _launchPads

    override suspend fun refreshLaunchPads() {
        withContext(ioDispatcher) {
            if (_launchPads.value == Result.NotStarted || _launchPads.value is Result.Error) {
                _launchPads.value = Result.Loading
                val result = suspendRunCatching { launchPadsNetwork.getLaunchPads() }
                if (result.isSuccess) {
                    _launchPads.value = Result.Success(mapper.map(result.getOrNull()!!))
                } else {
                    _launchPads.value = Result.Error(result.exceptionOrNull())
                }
            } else {
                _launchPads
            }
        }
    }

    private suspend fun <T> suspendRunCatching(block: suspend () -> T): kotlin.Result<T> = try {
        kotlin.Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        Log.i(
            "suspendRunCatching",
            "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result",
            exception
        )
        kotlin.Result.failure(exception)
    }

}
