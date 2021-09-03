package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.model.DetailData
import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.repository.ComicRepository
import com.jaime.marvelviewer.util.ErrorCode
import com.jaime.marvelviewer.util.Resource
import com.jaime.marvelviewer.util.Status
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class DetailViewModel : ViewModel() {
    private val characterRepository: CharacterRepository by inject(CharacterRepository::class.java)
    private val comicRepository: ComicRepository by inject(ComicRepository::class.java)
    private val dataFactory: DataFactory by inject(DataFactory::class.java)

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

            val characterItems = dataFactory.convertToCharacterItems(characterData.data)
            val characterResource = Resource(
                characterData.status, characterItems, characterData.errorCode
            )

            val comicItems = dataFactory.convertToComicItems(comicData.data)
            val comicResource = Resource(comicData.status, comicItems, comicData.errorCode)

            postData(
                characterData.status,
                comicData.status,
                DetailData(characterResource, comicResource)
            )
        }
    }

    /**
     * Logic to decide what resource to wrap the underlining data with, can be either [Status.SUCCESS] or [Status.ERROR]
     */
    private fun postData(characterStatus: Status, comicStatus: Status, data: DetailData) {
        val detailData = if (characterStatus == Status.SUCCESS && comicStatus == Status.SUCCESS) {
            Resource.success(null, data)
        } else {
            Resource.error(ErrorCode.ERROR_GENERIC, data)
        }

        _detailData.postValue(detailData)
    }
}
