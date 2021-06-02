package com.haithamghanem.extremesolutiontask.data.repository.dataSource

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getHeroCharacters(name: String, limit: Int): Response<APIResponse>
}