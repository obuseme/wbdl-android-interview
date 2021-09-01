package com.jaime.marvelviewer.di

import com.jaime.marvelviewer.ui.ComicDetailFragment
import com.jaime.marvelviewer.ui.ComicFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    fragment {
        ComicFragment()
    }
    fragment {
        ComicDetailFragment()
    }
}