package com.haithamghanem.extremesolutiontask.data.api

import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPIService {



    @GET("characters")
    suspend fun getHeroCharacters(
        @Query("limit") limit:Int,
        @Query("ts") ts:String = "",
        @Query("apikey") apikey:String = "ec22c60c3645811035cb5d0ae0cb780a",
        @Query("hash") hash:String = "",
        @Query("nameStartsWith") nameStartsWith:String? = null,
        @Query("offset") offset:Int = 0

    ): Response<APIResponse>
}