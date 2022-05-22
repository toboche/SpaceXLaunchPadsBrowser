package pl.toboche.spacexlauchpadsbrowser.core.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.toboche.spacexlauchpadsbrowser.core.data.mapping.LaunchPadsMapper
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.network.LaunchPadsNetwork
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import kotlin.coroutines.cancellation.CancellationException

class MemoryCachedLaunchPadsRepository(
    val launchPadsNetwork: LaunchPadsNetwork,
    val mapper: LaunchPadsMapper
) : LaunchPadsRepository {

    private var launchPads: List<LaunchPad>? = null

    override fun getLaunchPads(): Flow<Result<List<LaunchPad>>> {
        return flow {
            if (launchPads == null) {
                emit(Result.Loading)
                val result = suspendRunCatching { launchPadsNetwork.getLaunchPads() }
                if (result.isSuccess) {
                    emit(Result.Success(mapper.map(result.getOrNull()!!)))
                } else {
                    emit(Result.Error(result.exceptionOrNull()))
                }
            } else {
                launchPads
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
