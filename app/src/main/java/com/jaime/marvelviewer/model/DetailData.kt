package com.jaime.marvelviewer.model

import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.util.Resource

data class DetailData(
        val characters: Resource<List<Character>>? = null,
        val comics: Resource<List<Comic>>? = null
)