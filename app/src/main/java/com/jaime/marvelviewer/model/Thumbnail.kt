package com.jaime.marvelviewer.model

class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getFullImage(): String = "$path.$extension".replace("http", "https")
}