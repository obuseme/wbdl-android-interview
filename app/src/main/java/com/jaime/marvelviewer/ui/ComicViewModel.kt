package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.db.Comic
import com.jaime.marvelviewer.repository.ComicRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ComicViewModel: ViewModel() {
    private val repository: ComicRepository by inject(ComicRepository::class.java)

    private val _comicData = MutableLiveData<Resource<List<Comic>>>(Resource.loading())
    val comicData: LiveData<Resource<List<Comic>>> = _comicData

    init {
        getComicData()
    }

    fun getComicData() {
        viewModelScope.launch {
            _comicData.postValue(repository.requestComicData())
        }
    }
}