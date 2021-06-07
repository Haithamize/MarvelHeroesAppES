package com.haithamghanem.extremesolutiontask.data.repository

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.domain.repository.HeroCharactersRepo
import retrofit2.Response

class HeroCharactersRepoImpl(private val remoteDataSource: RemoteDataSource): HeroCharactersRepo {
    override suspend fun getHeroCharacters( limit: Int): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getHeroCharacters(limit))
    }

    override suspend fun getSearchedHeroCharacters(name: String, limit: Int): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getSearchedHeroCharacters(name, limit))
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