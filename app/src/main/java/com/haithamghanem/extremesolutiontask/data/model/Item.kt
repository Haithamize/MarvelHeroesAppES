package com.haithamghanem.extremesolutiontask.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    val name: String,
    val resourceURI: String
):Serializable