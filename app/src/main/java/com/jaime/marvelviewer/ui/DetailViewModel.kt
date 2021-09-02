package com.jaime.marvelviewer.ui

import androidx.lifecycle.*
import com.jaime.marvelviewer.model.DetailData
import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.repository.ComicRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class DetailViewModel: ViewModel() {
    private val characterRepository: CharacterRepository by inject(CharacterRepository::class.java)
    private val comicRepository: ComicRepository by inject(ComicRepository::class.java)

    private val _detailData = MutableLiveData<Resource<DetailData>>(Resource.loading())
    val detailData: LiveData<Resource<DetailData>> = _detailData

    /**
     * Request the full series details as per requirement
     * 'The UI of the screen should remain in a loading state until both API requests complete'
     * Both the character and comic endpoints are queried and only when both have a result, update Live Data
     */
    fun requestSeriesDetails(id: String) {
        viewModelScope.launch {
            val characterData = characterRepository.requestCharacterData(id)
            val comicData = comicRepository.requestComicData(id)
            _detailData.postValue(
                Resource.success(data = DetailData(characterData, comicData))
            )
        }
    }
}