package com.obss.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterLocation(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
): Serializable