package com.jaime.marvelviewer.model

data class ComicAPIResponse(
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ComicData
)