package pl.toboche.spacexlauchpadsbrowser.core.network.model

data class LaunchPadEntity(
    val id: Long,
    val name: String,
    val status: String,
    val location: LocationEntity
) {
    data class LocationEntity(
        val name: String,
        val region: String,
        val latitude: Double,
        val longitude: Double
    )
}
