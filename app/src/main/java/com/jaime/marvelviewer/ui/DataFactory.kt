package com.jaime.marvelviewer.ui

import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.ui.groupie.CharacterItem
import com.jaime.marvelviewer.ui.groupie.ComicItem
import com.jaime.marvelviewer.ui.groupie.SeriesItem

/**
 * Factory class to handle the processing of various manipulations
 * This class is intended to be injected by a ViewModel and processed in a coroutine off the main thread
 * This allows the UI thread to have maximum performance
 */
class DataFactory {
    /**
     * Convert series data from API into Groupie RecyclerView items
     * @param seriesData the data from the API
     * @return a list of Groupie recyclerview items
     */
    fun convertToSeriesItems(seriesData: List<Series>?): List<SeriesItem>? {
        seriesData?.let {
            val items = mutableListOf<SeriesItem>()
            it.forEach { series ->
                items.add(SeriesItem(series))
            }
            return if (items.isEmpty()) null
            else items
        }
        return null
    }

    /**
     * Convert character data from API into Groupie RecyclerView items
     * @param characterData the data from the API
     * @return a list of Groupie recyclerview items
     */
    fun convertToCharacterItems(characterData: List<Character>?): List<CharacterItem>? {
        characterData?.let {
            val items = mutableListOf<CharacterItem>()
            it.forEach { character ->
                items.add(CharacterItem(character))
            }
            return if (items.isEmpty()) null
            else items
        }
        return null
    }

    /**
     * Convert comic data from API into Groupie RecyclerView items
     * @param comicData the data from the API
     * @return a list of Groupie recyclerview items
     */
    fun convertToComicItems(comicData: List<Comic>?): List<ComicItem>? {
        comicData?.let {
            val items = mutableListOf<ComicItem>()
            it.forEach { comic ->
                items.add(ComicItem(comic))
            }
            return if (items.isEmpty()) null
            else items
        }
        return null
    }
}