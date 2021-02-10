package com.obss.rickandmorty.network

import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.model.EpisodeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): CharacterModel

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterObjectModel

    @GET
    suspend fun getSingleEpisode(@Url url: String): EpisodeModel

    @GET
    suspend fun getMoreCharacters(@Url url: String): CharacterModel

}