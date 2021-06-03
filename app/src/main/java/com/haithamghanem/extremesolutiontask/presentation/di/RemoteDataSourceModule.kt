package com.haithamghanem.extremesolutiontask.presentation.di

import com.haithamghanem.extremesolutiontask.data.api.MarvelAPIService
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import com.haithamghanem.extremesolutiontask.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideMarvelRemoteDataSource(marvelAPIService: MarvelAPIService): RemoteDataSource{
        return RemoteDataSourceImpl(marvelAPIService)
    }
}