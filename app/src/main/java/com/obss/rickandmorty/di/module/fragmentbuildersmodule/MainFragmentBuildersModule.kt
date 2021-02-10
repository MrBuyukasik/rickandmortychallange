package com.obss.rickandmorty.di.module.fragmentbuildersmodule

import com.obss.rickandmorty.ui.characterdetail.CharacterDetailFragment
import com.obss.rickandmorty.ui.characters.CharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailFragment(): CharacterDetailFragment

}
