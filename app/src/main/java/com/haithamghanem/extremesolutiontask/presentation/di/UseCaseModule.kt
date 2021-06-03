package com.haithamghanem.extremesolutiontask.presentation.di

import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo
import com.haithamghanem.extremesolutiontask.domain.usecase.GetHeroCharacterUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    fun provideGetHeroCharsUseCase(heroCharactersRepo: HeroCharactersRepo): GetHeroCharacterUseCase{
        return GetHeroCharacterUseCase(heroCharactersRepo)
    }
}