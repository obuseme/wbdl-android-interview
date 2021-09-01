package com.jaime.marvelviewer.model.character

import com.jaime.marvelviewer.model.Thumbnail
import com.jaime.marvelviewer.model.comic.ComicInfo

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicInfo
)