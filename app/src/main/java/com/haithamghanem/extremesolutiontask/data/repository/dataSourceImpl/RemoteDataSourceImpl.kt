package com.haithamghanem.extremesolutiontask.data.repository.dataSourceImpl

import com.haithamghanem.extremesolutiontask.data.api.MarvelAPIService
import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val marvelAPIService: MarvelAPIService, private val name: String , private val limit: Int): RemoteDataSource {
    override suspend fun getHeroCharacters(): Response<APIResponse> {
        return marvelAPIService.getHeroCharacters(name,limit)
    }
}