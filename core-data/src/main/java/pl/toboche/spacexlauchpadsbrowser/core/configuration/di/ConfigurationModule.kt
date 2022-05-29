package pl.toboche.spacexlauchpadsbrowser.core.configuration.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.toboche.spacexlauchpadsbrowser.core.configuration.ApplicationConfiguration
import pl.toboche.spacexlauchpadsbrowser.core.configuration.ProductionApplicationConfiguration

@Module
@InstallIn(SingletonComponent::class)
interface ConfigurationModule {

    @Binds
    fun bindsApplicationConfiguration(
        productionApplicationConfiguration: ProductionApplicationConfiguration
    ): ApplicationConfiguration
}