package com.haithamghanem.extremesolutiontask.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
):Serializable