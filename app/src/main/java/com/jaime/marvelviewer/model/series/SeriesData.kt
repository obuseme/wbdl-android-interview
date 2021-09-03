package com.jaime.marvelviewer.model.series

data class SeriesData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Series>? = null
)
