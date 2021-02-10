package com.obss.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterModel(

    @SerializedName("info")
    val info: CharacterInfoModel,

    @SerializedName("results")
    val results: List<CharacterObjectModel>?


)