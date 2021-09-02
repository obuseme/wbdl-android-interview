package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.repository.SeriesRepository
import com.jaime.marvelviewer.ui.groupie.SeriesItem
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SeriesViewModel: ViewModel() {
    private val repository: SeriesRepository by inject(SeriesRepository::class.java)
    private val dataFactory: DataFactory by inject(DataFactory::class.java)

    private val _seriesData = MutableLiveData<Resource<List<SeriesItem>>>(Resource.loading())
    val seriesData: LiveData<Resource<List<SeriesItem>>> = _seriesData

    init {
        getSeriesData()
    }

    fun getSeriesData() {
        viewModelScope.launch {
            val response = repository.requestSeriesData()
            val items = dataFactory.convertToSeriesItems(response.data)

            _seriesData.postValue(
                Resource(response.status, items, response.errorCode)
            )
        }
    }
}