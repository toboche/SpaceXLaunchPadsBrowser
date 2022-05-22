package pl.toboche.spacexlauchpadsbrowser.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.MemoryCachedLaunchPadsRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsLaunchPadsRepository(
        memoryCachedLaunchPadsRepository: MemoryCachedLaunchPadsRepository
    ): LaunchPadsRepository
}