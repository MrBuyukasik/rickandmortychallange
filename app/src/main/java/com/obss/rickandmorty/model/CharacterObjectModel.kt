package com.obss.rickandmorty.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class CharacterObjectModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("origin")
    val origin: CharacterOrigin?,
    @SerializedName("location")
    val location: CharacterLocation?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("episode")
    val episode: List<String>?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?,
    val lastEpisodeUrl: String?,
    val isFavorite: Boolean?
) : Serializable