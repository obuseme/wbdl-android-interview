package com.jaime.marvelviewer.model.series

import com.jaime.marvelviewer.model.Thumbnail

data class Series(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail,
    val comics: SeriesInfo,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String
)