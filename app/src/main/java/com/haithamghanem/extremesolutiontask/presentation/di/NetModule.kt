package com.haithamghanem.extremesolutiontask.presentation.di

import com.google.gson.GsonBuilder
import com.haithamghanem.extremesolutiontask.data.api.MarvelAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://gateway.marvel.com")
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): MarvelAPIService{
        return retrofit.create(MarvelAPIService::class.java)
    }
}