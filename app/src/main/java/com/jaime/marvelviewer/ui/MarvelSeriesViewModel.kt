package com.jaime.marvelviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.model.ComicData
import com.jaime.marvelviewer.repository.MarvelRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MarvelSeriesViewModel: ViewModel() {
    private val repository: MarvelRepository by inject(MarvelRepository::class.java)

    private val _comicData = MutableStateFlow<Resource<ComicData>>(Resource.loading(null))
    val comicData: StateFlow<Resource<ComicData>> = _comicData

    init {
        getComicData()
    }

    fun getComicData() {
        viewModelScope.launch {
            _comicData.value = repository.getComicData()
        }
    }
}