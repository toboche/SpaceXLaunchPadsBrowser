package pl.toboche.spacexlauchpadsbrowser.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LaunchPadEntity(
    val id: Long,
    val name: String,
    val status: String,
    val location: LocationEntity
) {
    @Serializable
    data class LocationEntity(
        val name: String,
        val region: String,
        val latitude: Double,
        val longitude: Double
    )
}
