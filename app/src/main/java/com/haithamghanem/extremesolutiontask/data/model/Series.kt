package com.haithamghanem.extremesolutiontask.data.model


import com.google.gson.annotations.SerializedName

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)