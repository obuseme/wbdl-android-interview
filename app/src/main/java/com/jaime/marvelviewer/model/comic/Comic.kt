package com.jaime.marvelviewer.model.comic

import com.jaime.marvelviewer.model.Thumbnail

data class Comic(
    val id: Int,
    val title: String,
    val issueNumber: String,
    val description: String,
    val format: String,
    val thumbnail: Thumbnail,
    val prices: List<Price>? = null
)