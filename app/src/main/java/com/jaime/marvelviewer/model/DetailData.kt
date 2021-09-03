package com.jaime.marvelviewer.model

import com.jaime.marvelviewer.ui.groupie.CharacterItem
import com.jaime.marvelviewer.ui.groupie.ComicItem
import com.jaime.marvelviewer.util.Resource

data class DetailData(
    val characters: Resource<List<CharacterItem>>? = null,
    val comics: Resource<List<ComicItem>>? = null
)
