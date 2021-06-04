package com.haithamghanem.extremesolutiontask.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.domain.usecase.GetHeroCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroCharactersViewModel( val app:Application,val getHeroCharacterUseCase: GetHeroCharacterUseCase): AndroidViewModel(app) {

    val marvelHeroCharacters: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getMarvelHeroCharacters( limit: Int) = viewModelScope.launch (Dispatchers.IO){

        marvelHeroCharacters.postValue(Resource.Loading())

        if(isNetworkAvailable(app)) {

            val apiResponse = getHeroCharacterUseCase.execute(limit)
            marvelHeroCharacters.postValue(apiResponse)
        }else{
            marvelHeroCharacters.postValue(Resource.Error("Internet is not available, please check your internet connection"))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
}