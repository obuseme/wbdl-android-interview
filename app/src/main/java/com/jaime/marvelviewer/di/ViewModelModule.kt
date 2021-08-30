package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.MarvelSeriesViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MarvelSeriesViewModel() }
}