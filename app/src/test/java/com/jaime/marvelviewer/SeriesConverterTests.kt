package com.jaime.marvelviewer

import com.jaime.marvelviewer.model.Thumbnail
import com.jaime.marvelviewer.model.series.Series
import com.jaime.marvelviewer.model.series.SeriesData
import com.jaime.marvelviewer.model.series.SeriesInfo
import com.jaime.marvelviewer.util.SeriesConverter
import org.junit.Test
import kotlin.test.assertEquals

/**
 * A simple test covering a full conversion of a [Series] into a [com.jaime.marvelviewer.db.Series]
 */
class SeriesConverterTests {

    private val converter = SeriesConverter

    private val id = 1
    private val title = "title"
    private val description = "description"
    private val thumbnailPath = "path"
    private val thumbnailExtension = "txt"
    private val available = 4
    private val startYear = 2000
    private val endYear = 2021
    private val rating = "R"

    // Mock Series from Model
    private val modelSeries = Series(id, title, description, Thumbnail(thumbnailPath, thumbnailExtension),
        SeriesInfo(available), startYear, endYear, rating)

    // Mock Series from DB
    private val dbSeries = com.jaime.marvelviewer.db.Series(id, title, description, "$thumbnailPath.$thumbnailExtension",
        startYear, endYear, rating, available)

    @Test
    fun `Given a required conversion, it is successfully converted`() {
        // Generate series list from Model
        val series = mutableListOf<Series>()
        series.add(modelSeries)
        val seriesData = SeriesData(1,1,1,1,series)

        // Perform the convert with Model data
        val convertResult = converter.convertToDBSeriesList(seriesData)

        // Generate series list from DB
        val seriesFromDB = mutableListOf<com.jaime.marvelviewer.db.Series>()
        seriesFromDB.add(dbSeries)

        assertEquals(convertResult, seriesFromDB)
    }
}