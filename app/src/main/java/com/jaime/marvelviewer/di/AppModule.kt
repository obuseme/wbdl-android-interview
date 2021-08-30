package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.api.MarvelRetrofitBuilder
import org.koin.dsl.module

val appModule = module {
    single {
        provideRetrofitBuilder()
    }
    single {
        provideMarvelService(get())
    }
}

private fun provideRetrofitBuilder(): MarvelRetrofitBuilder =
    MarvelRetrofitBuilder()

private fun provideMarvelService(retrofitBuilder: MarvelRetrofitBuilder): MarvelAPI =
    retrofitBuilder.buildService(MarvelAPI::class.java)