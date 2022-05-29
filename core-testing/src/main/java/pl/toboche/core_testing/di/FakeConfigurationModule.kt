package pl.toboche.core_testing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import pl.toboche.spacexlauchpadsbrowser.core.configuration.ApplicationConfiguration
import pl.toboche.spacexlauchpadsbrowser.core.configuration.di.ConfigurationModule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConfigurationModule::class],
)
class FakeConfigurationModule {

    @Provides
    @Singleton
    fun provideUrl(): ApplicationConfiguration = object : ApplicationConfiguration {
        override fun getBaseUrl() = "http://localhost:8080/"
    }
}