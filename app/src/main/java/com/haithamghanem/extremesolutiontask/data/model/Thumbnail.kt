package com.haithamghanem.extremesolutiontask.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Thumbnail(
    val extension: String,
    val path: String
): Serializable