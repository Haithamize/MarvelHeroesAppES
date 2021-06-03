package com.haithamghanem.extremesolutiontask.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haithamghanem.extremesolutiontask.domain.usecase.GetHeroCharacterUseCase

class HeroCharactersViewModelFactory( val app:Application,val getHeroCharacterUseCase: GetHeroCharacterUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroCharactersViewModel(app, getHeroCharacterUseCase) as T
    }
}