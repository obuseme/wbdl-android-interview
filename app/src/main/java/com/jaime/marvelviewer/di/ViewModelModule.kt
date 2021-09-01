package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.CharacterViewModel
import com.jaime.marvelviewer.ui.ComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComicViewModel() }
    viewModel { CharacterViewModel() }
}