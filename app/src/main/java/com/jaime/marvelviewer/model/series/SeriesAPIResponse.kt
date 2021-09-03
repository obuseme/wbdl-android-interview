package com.jaime.marvelviewer.model.series

data class SeriesAPIResponse(
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: SeriesData
)
