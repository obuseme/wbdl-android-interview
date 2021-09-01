package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.repository.SeriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SeriesRepository(get(), get()) }
    single { CharacterRepository(get()) }
}