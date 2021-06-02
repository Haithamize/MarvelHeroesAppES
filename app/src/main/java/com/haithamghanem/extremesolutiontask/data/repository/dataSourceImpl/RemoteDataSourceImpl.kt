package com.haithamghanem.extremesolutiontask.data.repository.dataSourceImpl

import com.haithamghanem.extremesolutiontask.data.api.MarvelAPIService
import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val marvelAPIService: MarvelAPIService): RemoteDataSource {
    override suspend fun getHeroCharacters(name: String, limit: Int): Response<APIResponse> {
        return marvelAPIService.getHeroCharacters(name,limit)
    }
}