package com.obss.rickandmorty.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.obss.rickandmorty.base.BaseViewModel
import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.model.EpisodeModel
import com.obss.rickandmorty.network.helper.DataHolder
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val characterDetailsRepository: CharacterDetailRepository) :
    BaseViewModel() {

    val character: MutableLiveData<CharacterObjectModel> = MutableLiveData()
    val episode: MutableLiveData<EpisodeModel> = MutableLiveData()

    var characterDetailImage: String? = null

    fun getCharacterById(
        characterId: Int
    ) {
        viewModelScope.launch {
            val response =
                characterDetailsRepository.getCharactersById(
                    characterId,
                    this@CharacterDetailViewModel
                )

            response.let {
                if (it is DataHolder.Success) {
                    val lastEpisodeUrl = it.body.episode?.last()
                    character.postValue(it.body)
                    lastEpisodeUrl?.let {
                      getSingleEpisode(lastEpisodeUrl)
                    }
                }
            }
        }
    }

    fun getCharacterDetailImage(imageUrl: String) {
        characterDetailImage = imageUrl
    }

    private fun getSingleEpisode(episodeUrl: String) {
        viewModelScope.launch {
            val response =
                characterDetailsRepository.getSingleEpisode(
                    episodeUrl,
                    this@CharacterDetailViewModel
                )

            response.let {
                if (it is DataHolder.Success) {
                    episode.postValue(it.body)
                }
            }
        }
    }

}