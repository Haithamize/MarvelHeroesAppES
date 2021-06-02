package com.haithamghanem.extremesolutiontask.data.repository

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo
import retrofit2.Response

class HeroCharactersRepoImpl(private val remoteDataSource: RemoteDataSource): HeroCharactersRepo {
    override suspend fun getHeroCharacters(): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getHeroCharacters())
    }

    override suspend fun getSearchedHeroCharacters(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    private fun responseToResource(response: Response<APIResponse>) : Resource<APIResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}