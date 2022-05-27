package pl.toboche.spacexlauchpadsbrowser.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchPadEntity(
    val id: Long,
    val name: String,
    val status: String,
    val location: LocationEntity,
    @SerialName("site_name_long") val fullName: String
) {
    @Serializable
    data class LocationEntity(
        val name: String,
        val region: String,
        val latitude: Double,
        val longitude: Double
    )
}
