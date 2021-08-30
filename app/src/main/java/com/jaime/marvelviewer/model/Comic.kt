package com.jaime.marvelviewer.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: ComicThumbnail,
    val comics: ComicInfo,
    val startYear: Int?,
    val endYear: Int?
)