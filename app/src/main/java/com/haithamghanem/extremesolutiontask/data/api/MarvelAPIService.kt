package com.haithamghanem.extremesolutiontask.data.api

import com.google.gson.internal.GsonBuildConfig
import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPIService {

    @GET("v1/public/characters")
    suspend fun getHeroCharacters(
            @Query("name")
            name: String="",
            @Query("limit")
            limit: Int,
            @Query("apikey")
            apiKey: String = "ec22c60c3645811035cb5d0ae0cb780a" ,
            @Query("offset")
            offset: Int = 0
    ): Response<APIResponse>
}