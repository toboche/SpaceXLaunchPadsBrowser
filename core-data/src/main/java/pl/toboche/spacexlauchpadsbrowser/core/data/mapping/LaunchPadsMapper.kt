package pl.toboche.spacexlauchpadsbrowser.core.data.mapping

import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.network.model.LaunchPadEntity
import javax.inject.Inject

class LaunchPadsMapper @Inject constructor() {
    fun map(launchPadEntities: List<LaunchPadEntity>) =
        launchPadEntities.map {
            LaunchPad(
                it.id,
                it.name,
                mapStatus(it.status),
                mapLocation(it.location)
            )
        }

    private fun mapLocation(location: LaunchPadEntity.LocationEntity) =
        LaunchPad.Location(
            location.name,
            location.region,
            location.latitude,
            location.longitude
        )

    private fun mapStatus(status: String) =
        when (status) {
            "active" -> LaunchPad.Status.ACTIVE
            "retired" -> LaunchPad.Status.RETIRED
            "under_construction" -> LaunchPad.Status.UNDER_CONSTRUCTION
            else -> LaunchPad.Status.UNKNOWN
        }
}
