package com.obss.rickandmorty.di.scope

import android.app.Application
import com.obss.rickandmorty.RickAndMortyApplication
import com.obss.rickandmorty.di.module.ActivityModule
import com.obss.rickandmorty.di.module.AppModule
import com.obss.rickandmorty.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetworkModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(initApplication: RickAndMortyApplication)
}