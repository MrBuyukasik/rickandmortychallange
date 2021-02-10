package com.obss.rickandmorty.interfaces

import com.obss.rickandmorty.model.CharacterModel
import com.obss.rickandmorty.model.CharacterObjectModel

interface ICharacters {
    fun onCharactersClicked(characters: CharacterObjectModel)
}