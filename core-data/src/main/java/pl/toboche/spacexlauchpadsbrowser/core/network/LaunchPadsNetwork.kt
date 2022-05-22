package pl.toboche.spacexlauchpadsbrowser.core.network

import pl.toboche.spacexlauchpadsbrowser.core.network.model.LaunchPadEntity

interface LaunchPadsNetwork {
    suspend fun getLaunchPads(): List<LaunchPadEntity>
}