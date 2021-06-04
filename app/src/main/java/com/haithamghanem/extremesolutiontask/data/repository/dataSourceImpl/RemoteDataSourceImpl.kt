package com.haithamghanem.extremesolutiontask.data.repository.dataSourceImpl

import android.util.Log
import com.haithamghanem.extremesolutiontask.data.api.MarvelAPIService
import com.haithamghanem.extremesolutiontask.data.model.APIResponse
import com.haithamghanem.extremesolutiontask.data.repository.dataSource.RemoteDataSource
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class RemoteDataSourceImpl(private val marvelAPIService: MarvelAPIService): RemoteDataSource {
    override suspend fun getHeroCharacters(limit: Int): Response<APIResponse> {
        Log.d("sayed", "${getTimestamp()},,,, ${getMD5hash(getTimestamp() + "21efccf3a398cc3212b67097c88859791cf13f0e" + "ec22c60c3645811035cb5d0ae0cb780a")} ")
        return marvelAPIService.getHeroCharacters(limit,ts = getTimestamp(), hash = getMD5hash(getTimestamp() + "21efccf3a398cc3212b67097c88859791cf13f0e" + "ec22c60c3645811035cb5d0ae0cb780a"))
    }


    fun getMD5hash(s: String): String {
        var messageDigest: MessageDigest? = null
        try {
            messageDigest = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        messageDigest!!.update(s.toByteArray(), 0, s.length)
        return BigInteger(1, messageDigest!!.digest()).toString(16)
    }

    private fun getTimestamp(): String {
        return (System.currentTimeMillis() / 1000).toString()
    }
}