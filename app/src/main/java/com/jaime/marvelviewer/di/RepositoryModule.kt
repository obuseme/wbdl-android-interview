package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.repository.MarvelRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MarvelRepository(get()) }
}