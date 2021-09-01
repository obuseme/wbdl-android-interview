package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.repository.ComicRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class DetailViewModel: ViewModel() {
    private val characterRepository: CharacterRepository by inject(CharacterRepository::class.java)
    private val comicRepository: ComicRepository by inject(ComicRepository::class.java)

    private val _characterData = MutableLiveData<Resource<List<Character>>>(Resource.loading())
    val characterData: LiveData<Resource<List<Character>>> = _characterData

    private val _comicData = MutableLiveData<Resource<List<Comic>>>(Resource.loading())
    val comicData: LiveData<Resource<List<Comic>>> = _comicData

    fun getCharacterData(id: String) {
        viewModelScope.launch {
            _characterData.postValue(characterRepository.requestCharacterData(id))
        }
    }

    fun getComicData(id: String) {
        viewModelScope.launch {
            _comicData.postValue(comicRepository.requestComicData(id))
        }
    }
}