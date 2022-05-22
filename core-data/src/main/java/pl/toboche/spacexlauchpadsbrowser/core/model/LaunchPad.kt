package pl.toboche.spacexlauchpadsbrowser.core.model

data class LaunchPad(
    val id: Long,
    val name: String,
    val status: Status,
    val location: Location
) {
    enum class Status {
        ACTIVE,
        RETIRED,
        UNDER_CONSTRUCTION,
        UNKNOWN
    }

    data class Location(
        val name: String,
        val region: String,
        val latitude: Double,
        val longitude: Double
    )
}
