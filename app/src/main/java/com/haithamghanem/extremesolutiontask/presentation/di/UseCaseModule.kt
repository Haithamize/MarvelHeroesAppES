package com.haithamghanem.extremesolutiontask.presentation.di

import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo
import com.haithamghanem.extremesolutiontask.domain.usecase.GetHeroCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHeroCharsUseCase(heroCharactersRepo: HeroCharactersRepo): GetHeroCharacterUseCase{
        return GetHeroCharacterUseCase(heroCharactersRepo)
    }
}