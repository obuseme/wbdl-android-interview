package com.jaime.marvelviewer

import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.model.Thumbnail
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.model.series.SeriesInfo
import com.jaime.marvelviewer.ui.DataFactory
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.Test

/**
 * Tests covering different states of data that can be send to the data factory for conversion
 * There are three types
 * 1) A null value
 * 2) An empty list
 * 3) A list with relevant entries in it for conversion
 */
class DataFactoryTests {

    private val dataFactory = DataFactory()

    @Test
    fun `Given a null series list with no items, null is returned`() {
        val result = dataFactory.convertToSeriesItems(null)
        assertNull(result)
    }

    @Test
    fun `Given a formed series list with no items, null is returned`() {
        val seriesList = mutableListOf<Series>()
        val result = dataFactory.convertToSeriesItems(seriesList)
        assertNull(result)
    }

    @Test
    fun `Given a formed series list with 1 item, a list with 1 item is returned`() {
        val series = Series(1, "Title", "Description", "test.txt", 1, 1, "R", 1)
        val seriesList = mutableListOf<Series>()
        seriesList.add(series)

        val result = dataFactory.convertToSeriesItems(seriesList)
        assertNotNull(result)
        assertTrue(result.size == 1)
    }

    @Test
    fun `Given a null character list with no items, null is returned`() {
        val result = dataFactory.convertToCharacterItems(null)
        assertNull(result)
    }

    @Test
    fun `Given a formed character list with no items, null is returned`() {
        val characterList = mutableListOf<Character>()
        val result = dataFactory.convertToCharacterItems(characterList)
        assertNull(result)
    }

    @Test
    fun `Given a formed character list with 1 item, a list with 1 item is returned`() {
        val character = Character(
            1, "Title", "Description", Thumbnail("test", "txt"), SeriesInfo(5)
        )
        val characterList = mutableListOf<Character>()
        characterList.add(character)

        val result = dataFactory.convertToCharacterItems(characterList)
        assertNotNull(result)
        assertTrue(result.size == 1)
    }

    @Test
    fun `Given a null comic list with no items, null is returned`() {
        val result = dataFactory.convertToComicItems(null)
        assertNull(result)
    }

    @Test
    fun `Given a formed comic list with no items, null is returned`() {
        val comicList = mutableListOf<Comic>()
        val result = dataFactory.convertToComicItems(comicList)
        assertNull(result)
    }

    @Test
    fun `Given a formed comic list with 1 item, a list with 1 item is returned`() {
        val comic = Comic(
            1, "Title", "10000000", "Description", "Format", Thumbnail("test", "txt"), null
        )
        val comicList = mutableListOf<Comic>()
        comicList.add(comic)

        val result = dataFactory.convertToComicItems(comicList)
        assertNotNull(result)
        assertTrue(result.size == 1)
    }
}
