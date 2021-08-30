package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.ComicFragment
import org.koin.dsl.module

val fragmentModule = module {
    factory {
        ComicFragment()
    }
}