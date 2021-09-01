package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.repository.ComicRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ComicRepository(get(), get()) }
    single { CharacterRepository(get()) }
}