package com.jaime.marvelviewer.model.character

import com.jaime.marvelviewer.model.Thumbnail
import com.jaime.marvelviewer.model.series.SeriesInfo

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: SeriesInfo
)
