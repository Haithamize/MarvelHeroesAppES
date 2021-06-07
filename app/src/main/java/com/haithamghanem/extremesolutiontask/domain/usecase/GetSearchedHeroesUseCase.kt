package com.haithamghanem.extremesolutiontask.domain.usecase

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo

class GetSearchedHeroesUseCase(private val heroCharactersRepo: HeroCharactersRepo) {

    suspend fun execute(searchQuery: String, limit: Int): Resource<APIResponse> =
         heroCharactersRepo.getSearchedHeroCharacters(searchQuery, limit )
}