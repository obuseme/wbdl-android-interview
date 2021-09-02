package com.jaime.marvelviewer.ui

import com.jaime.marvelviewer.db.Series
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
            seriesData.forEach {
                items.add(SeriesItem(it))
            }
            return items
        }
        return null
    }
}