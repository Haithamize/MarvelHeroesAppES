package com.haithamghanem.extremesolutiontask.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
): Serializable