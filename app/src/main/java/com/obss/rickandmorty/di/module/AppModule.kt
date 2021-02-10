package com.obss.rickandmorty.di.module

import android.app.Application
import android.content.Context
import com.obss.rickandmorty.BuildConfig
import com.obss.rickandmorty.di.qualifiers.BaseUrlQualifier
import com.obss.rickandmorty.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @AppScope
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @AppScope
    @BaseUrlQualifier
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

}