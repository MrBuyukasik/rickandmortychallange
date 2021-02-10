package com.obss.rickandmorty.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.obss.rickandmorty.base.BaseViewModel
import com.obss.rickandmorty.model.CharacterInfoModel
import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.network.helper.DataHolder
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    BaseViewModel() {

    val characterList: MutableLiveData<List<CharacterObjectModel>> = MutableLiveData()
    val characterListInfo: MutableLiveData<CharacterInfoModel> = MutableLiveData()
    val allCharacterList: MutableLiveData<List<CharacterObjectModel>> = MutableLiveData()


    fun getAllCharacters() {
        viewModelScope.launch {
            val response = charactersRepository.getAllCharacters(this@CharactersViewModel)

            response.let {
                if (it is DataHolder.Success) {
                    if (!it.body.results.isNullOrEmpty()) {
                        characterList.postValue(it.body.results)
                        allCharacterList.postValue(it.body.results)
                        characterListInfo.postValue(it.body.info)
                    }
                }
            }
        }
    }

    fun getMoreCharacters(nextCharacterPageUrl: String) {
        viewModelScope.launch {
            val response = charactersRepository.getMoreCharacters(
                nextCharacterPageUrl,
                this@CharactersViewModel
            )

            response.let {
                if (it is DataHolder.Success) {

                    if (!it.body.results.isNullOrEmpty()) {
                        characterList.postValue(characterList.value?.plus(it.body.results))
                        allCharacterList.postValue(characterList.value?.plus(it.body.results))
                        characterListInfo.postValue(it.body.info)
                    }
                }
            }
        }
    }

}
