package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.repository.SeriesRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SeriesViewModel: ViewModel() {
    private val repository: SeriesRepository by inject(SeriesRepository::class.java)

    private val _comicData = MutableLiveData<Resource<List<Series>>>(Resource.loading())
    val seriesData: LiveData<Resource<List<Series>>> = _comicData

    init {
        getComicData()
    }

    fun getComicData() {
        viewModelScope.launch {
            _comicData.postValue(repository.requestSeriesData())
        }
    }
}