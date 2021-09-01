package com.jaime.marvelviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.repository.CharacterRepository
import com.jaime.marvelviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class CharacterViewModel: ViewModel() {
    private val repository: CharacterRepository by inject(CharacterRepository::class.java)

    private val _characterData = MutableLiveData<Resource<List<Character>>>(Resource.loading())
    val characterData: LiveData<Resource<List<Character>>> = _characterData

    fun getCharacterData(id: String) {
        viewModelScope.launch {
            _characterData.postValue(repository.requestCharacterData(id))
        }
    }
}