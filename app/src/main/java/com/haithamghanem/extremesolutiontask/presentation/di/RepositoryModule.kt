package com.haithamghanem.extremesolutiontask.presentation.di

import com.haithamghanem.extremesolutiontask.data.repository.HeroCharactersRepoImpl
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): HeroCharactersRepo{
        return HeroCharactersRepoImpl(remoteDataSource)
    }
}