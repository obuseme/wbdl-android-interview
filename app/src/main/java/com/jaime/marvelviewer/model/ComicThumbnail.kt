package com.jaime.marvelviewer.model

class ComicThumbnail(
    val path: String,
    val extension: String
) {
    fun getFullImage(): String = "$path.$extension".replace("http", "https")
}