package com.obss.rickandmorty.di.module

import com.obss.rickandmorty.MainActivity
import com.obss.rickandmorty.di.module.fragmentbuildersmodule.MainFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): MainActivity
}