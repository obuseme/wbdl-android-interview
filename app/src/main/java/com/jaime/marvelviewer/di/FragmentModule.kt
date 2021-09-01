package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.fragments.DetailFragment
import com.jaime.marvelviewer.ui.fragments.SeriesFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    fragment {
        SeriesFragment()
    }
    fragment {
        DetailFragment()
    }
}