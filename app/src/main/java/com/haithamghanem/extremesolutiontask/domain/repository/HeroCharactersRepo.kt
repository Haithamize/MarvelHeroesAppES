package com.haithamghanem.extremesolutiontask.domain.repository

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.util.Resource

interface HeroCharactersRepo {

    suspend fun getHeroCharacters( limit: Int) : Resource<APIResponse>
    suspend fun getSearchedHeroCharacters(searchQuery: String) : Resource<APIResponse>
}