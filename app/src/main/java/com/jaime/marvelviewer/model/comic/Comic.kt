package com.jaime.marvelviewer.model.comic

import com.jaime.marvelviewer.model.Thumbnail

data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail,
    val comics: ComicInfo,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String
)