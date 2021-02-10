package com.obss.rickandmorty.ui.characters

import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.network.ApiService
import com.obss.rickandmorty.network.INetworkResponseHandling
import com.obss.rickandmorty.network.helper.DataHolder
import com.obss.rickandmorty.network.helper.RequestHelper
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCharacters(iNetworkResponseHandling: INetworkResponseHandling): DataHolder<CharacterModel>? {
        return object : RequestHelper<CharacterModel>() {
            override suspend fun createNetworkRequest(): CharacterModel {
                return apiService.getAllCharacters()
            }
        }.loadRequest(iNetworkResponseHandling, true)
    }

    suspend fun getMoreCharacters(
        nextCharacterPageUrl: String,
        iNetworkResponseHandling: INetworkResponseHandling
    ): DataHolder<CharacterModel>? {
        return object : RequestHelper<CharacterModel>() {
            override suspend fun createNetworkRequest(): CharacterModel {
                return apiService.getMoreCharacters(nextCharacterPageUrl)
            }
        }.loadRequest(iNetworkResponseHandling, true)
    }
}