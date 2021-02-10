package com.obss.rickandmorty.ui.characterdetail

import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.model.EpisodeModel
import com.obss.rickandmorty.network.ApiService
import com.obss.rickandmorty.network.INetworkResponseHandling
import com.obss.rickandmorty.network.helper.DataHolder
import com.obss.rickandmorty.network.helper.RequestHelper
import javax.inject.Inject


class CharacterDetailRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCharactersById(
        characterId: Int,
        iNetworkResponseHandling: INetworkResponseHandling
    ): DataHolder<CharacterObjectModel>? {
        return object : RequestHelper<CharacterObjectModel>() {
            override suspend fun createNetworkRequest(): CharacterObjectModel {
                return apiService.getCharacter(characterId)
            }

        }.loadRequest(iNetworkResponseHandling, true)
    }

    suspend fun getSingleEpisode(
        episodeUrl: String,
        iNetworkResponseHandling: INetworkResponseHandling
    ): DataHolder<EpisodeModel>? {
        return object : RequestHelper<EpisodeModel>() {
            override suspend fun createNetworkRequest(): EpisodeModel {
                return apiService.getSingleEpisode(episodeUrl)
            }

        }.loadRequest(iNetworkResponseHandling, true)
    }

}
