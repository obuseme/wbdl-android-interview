package com.jaime.marvelviewer.model.character

data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>? = null
)
