package com.haithamghanem.extremesolutiontask.domain.usecase

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo

class GetHeroCharacterUseCase(private val heroCharactersRepo: HeroCharactersRepo) {

    suspend fun execute(limit: Int): Resource<APIResponse> = heroCharactersRepo.getHeroCharacters( limit)

}