package com.jaime.marvelviewer.model.comic

data class ComicAPIResponse(
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ComicData
)
