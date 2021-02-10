package com.obss.rickandmorty.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.obss.rickandmorty.di.qualifiers.ViewModelKey
import com.obss.rickandmorty.ui.characterdetail.CharacterDetailViewModel
import com.obss.rickandmorty.ui.characters.CharactersViewModel
import com.obss.rickandmorty.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(CharactersViewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(CharacterDetailViewModel: CharacterDetailViewModel): ViewModel

}