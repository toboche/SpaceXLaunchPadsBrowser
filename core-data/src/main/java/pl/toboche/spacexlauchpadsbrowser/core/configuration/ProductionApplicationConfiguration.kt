package pl.toboche.spacexlauchpadsbrowser.core.configuration

import pl.toboche.core.data.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductionApplicationConfiguration
@Inject constructor() : ApplicationConfiguration {
    override fun getBaseUrl() = BuildConfig.BACKEND_URL
}