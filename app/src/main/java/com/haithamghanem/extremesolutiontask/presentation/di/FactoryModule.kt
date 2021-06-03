package com.haithamghanem.extremesolutiontask.presentation.di

import android.app.Application
import com.haithamghanem.extremesolutiontask.domain.usecase.GetHeroCharacterUseCase
import com.haithamghanem.extremesolutiontask.presentation.viewmodel.HeroCharactersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideHeroCharactersViewModel( app: Application,  getHeroCharacterUseCase: GetHeroCharacterUseCase): HeroCharactersViewModelFactory{
        return HeroCharactersViewModelFactory(app, getHeroCharacterUseCase)
    }
}