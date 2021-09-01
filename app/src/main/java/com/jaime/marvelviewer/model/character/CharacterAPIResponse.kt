package com.jaime.marvelviewer.model.character

data class CharacterAPIResponse(
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharacterData
)