package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.CharacterViewModel
import com.jaime.marvelviewer.ui.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SeriesViewModel() }
    viewModel { CharacterViewModel() }
}