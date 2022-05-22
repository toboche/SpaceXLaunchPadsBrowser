package pl.toboche.spacexlauchpadsbrowser.core.network

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LaunchPadsNetworkModule {

    @Binds
    fun bindsLaunchPadsNetwork(
        launchPadsRetrofitNetwork: LaunchPadsRetrofitNetwork
    ): LaunchPadsNetwork

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}